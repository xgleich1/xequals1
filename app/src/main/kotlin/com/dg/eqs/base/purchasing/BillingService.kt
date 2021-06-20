package com.dg.eqs.base.purchasing

import android.app.Activity
import androidx.annotation.StringRes
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.BillingResponseCode.*
import com.android.billingclient.api.BillingClient.SkuType.INAPP
import com.dg.eqs.base.purchasing.result.ConsumeResult
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFailed
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFinished
import com.dg.eqs.base.purchasing.result.PurchaseResult
import com.dg.eqs.base.purchasing.result.PurchaseResult.*
import com.dg.eqs.base.purchasing.result.SkuDetailsResult
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsMissing
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsPresent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class BillingService(
        private val billingClientBuilder: BillingClientBuilder,
        private val billingParamsBuilder: BillingParamsBuilder,
        private val billingResultTracking: BillingResultTracking) {

    suspend fun loadInAppSkuDetails(@StringRes vararg skuRes: Int): SkuDetailsResult {
        val billingClient = billingClientBuilder.build()

        billingClient.startConnection()

        val inAppSkuDetailsParams = billingParamsBuilder
                .buildInAppSkuDetailsParams(*skuRes)

        val inAppSkuDetailsResult = billingClient
                .loadSkuDetails(inAppSkuDetailsParams)

        billingClient.endConnection()

        trackSkuDetailsResult(inAppSkuDetailsResult)

        return inAppSkuDetailsResult
    }

    suspend fun launchPurchaseFlow(activity: Activity, skuDetails: SkuDetails): PurchaseResult {
        val purchaseResultChannel = Channel<PurchaseResult>(CONFLATED)

        val billingClient = billingClientBuilder.build { billingResult, purchases ->
            when {
                billingResult.responseCode == USER_CANCELED ->
                    purchaseResultChannel.offer(PurchaseCanceled(billingResult))

                billingResult.responseCode == ITEM_ALREADY_OWNED ->
                    purchaseResultChannel.offer(PurchasePending(billingResult))

                !purchases.isNullOrEmpty() ->
                    purchaseResultChannel.offer(PurchaseFinished(purchases))

                else -> purchaseResultChannel.offer(PurchaseFailed(billingResult))
            }
        }

        billingClient.startConnection()

        val billingFlowParams = billingParamsBuilder
                .buildBillingFlowParams(skuDetails)

        billingClient.launchBillingFlow(activity, billingFlowParams)

        billingClient.endConnection()

        val purchaseResult = purchaseResultChannel.receive()

        trackPurchaseResult(purchaseResult)

        return purchaseResult
    }

    suspend fun consumePurchase(purchase: Purchase): ConsumeResult {
        val billingClient = billingClientBuilder.build()

        billingClient.startConnection()

        val consumeParams = billingParamsBuilder
                .buildConsumeParams(purchase)

        val consumeResult = billingClient
                .consumePurchase(consumeParams)

        billingClient.endConnection()

        trackConsumeResult(consumeResult)

        return consumeResult
    }

    suspend fun consumePendingInAppPurchases() {
        val billingClient = billingClientBuilder.build()

        billingClient.startConnection()

        val purchases = billingClient
                .queryPurchases(INAPP)
                .purchasesList

        billingClient.endConnection()

        purchases?.forEach {
            consumePurchase(it)
        }
    }

    private suspend fun BillingClient.startConnection() =
            suspendCancellableCoroutine<Unit> { continuation ->
                startConnection(object : BillingClientStateListener {
                    override fun onBillingSetupFinished(billingResult: BillingResult) {
                        if(continuation.isActive) {
                            continuation.resume(Unit)
                        }
                    }

                    override fun onBillingServiceDisconnected() {
                        if(continuation.isActive) {
                            continuation.resume(Unit)
                        }
                    }
                })
            }

    private suspend fun BillingClient.loadSkuDetails(skuDetailsParams: SkuDetailsParams) =
            suspendCoroutine<SkuDetailsResult> { continuation ->
                querySkuDetailsAsync(skuDetailsParams) { billingResult, skuDetails ->
                    if(!skuDetails.isNullOrEmpty()) {
                        continuation.resume(SkuDetailsPresent(skuDetails))
                    } else {
                        continuation.resume(SkuDetailsMissing(billingResult))
                    }
                }
            }

    private suspend fun BillingClient.consumePurchase(consumeParams: ConsumeParams) =
            suspendCoroutine<ConsumeResult> { continuation ->
                consumeAsync(consumeParams) { billingResult, purchaseToken ->
                    if(billingResult.responseCode == OK) {
                        continuation.resume(ConsumeFinished(purchaseToken))
                    } else {
                        continuation.resume(ConsumeFailed(billingResult))
                    }
                }
            }

    private fun trackSkuDetailsResult(skuDetailsResult: SkuDetailsResult) =
            billingResultTracking.trackSkuDetailsResult(skuDetailsResult)

    private fun trackPurchaseResult(purchaseResult: PurchaseResult) =
            billingResultTracking.trackPurchaseResult(purchaseResult)

    private fun trackConsumeResult(consumeResult: ConsumeResult) =
            billingResultTracking.trackConsumeResult(consumeResult)
}