package com.dg.eqs.base.purchasing

import com.android.billingclient.api.BillingClient.BillingResponseCode.*
import com.android.billingclient.api.BillingResult
import com.dg.eqs.base.purchasing.result.ConsumeResult
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFailed
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFinished
import com.dg.eqs.base.purchasing.result.PurchaseResult
import com.dg.eqs.base.purchasing.result.PurchaseResult.*
import com.dg.eqs.base.purchasing.result.SkuDetailsResult
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsMissing
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsPresent
import com.dg.eqs.base.tracking.Tracking


class BillingResultTracking(private val tracking: Tracking) {
    fun trackSkuDetailsResult(result: SkuDetailsResult) = when(result) {
        is SkuDetailsPresent -> trackSkuDetailsPresent()
        is SkuDetailsMissing -> trackSkuDetailsMissing(result.billingResult)
    }

    fun trackPurchaseResult(result: PurchaseResult) = when(result) {
        is PurchaseFinished -> trackPurchaseFinished()
        is PurchaseCanceled -> trackPurchaseCanceled(result.billingResult)
        is PurchasePending -> trackPurchasePending(result.billingResult)
        is PurchaseFailed -> trackPurchaseFailed(result.billingResult)
    }

    fun trackConsumeResult(result: ConsumeResult) = when(result) {
        is ConsumeFinished -> trackConsumeFinished()
        is ConsumeFailed -> trackConsumeFailed(result.billingResult)
    }

    private fun trackSkuDetailsPresent() =
            tracking.trackEvent("sku_details_present")

    private fun trackSkuDetailsMissing(billingResult: BillingResult) =
            tracking.trackEvent("sku_details_missing_${billingResult.toTrackingString()}")

    private fun trackPurchaseFinished() =
            tracking.trackEvent("purchase_finished")

    private fun trackPurchaseCanceled(billingResult: BillingResult) =
            tracking.trackEvent("purchase_canceled_${billingResult.toTrackingString()}")

    private fun trackPurchasePending(billingResult: BillingResult) =
            tracking.trackEvent("purchase_pending_${billingResult.toTrackingString()}")

    private fun trackPurchaseFailed(billingResult: BillingResult) =
            tracking.trackEvent("purchase_failed_${billingResult.toTrackingString()}")

    private fun trackConsumeFinished() =
            tracking.trackEvent("consume_finished")

    private fun trackConsumeFailed(billingResult: BillingResult) =
            tracking.trackEvent("consume_failed_${billingResult.toTrackingString()}")

    private fun BillingResult.toTrackingString() = when(responseCode) {
        SERVICE_TIMEOUT -> "service_timeout"
        FEATURE_NOT_SUPPORTED -> "feature_not_supported"
        SERVICE_DISCONNECTED -> "service_disconnected"
        OK -> "ok"
        USER_CANCELED -> "user_canceled"
        SERVICE_UNAVAILABLE -> "service_unavailable"
        BILLING_UNAVAILABLE -> "billing_unavailable"
        ITEM_UNAVAILABLE -> "item_unavailable"
        DEVELOPER_ERROR -> "developer_error"
        ERROR -> "error"
        ITEM_ALREADY_OWNED -> "item_already_owned"
        ITEM_NOT_OWNED -> "item_not_owned"

        else -> "unknown_response_code"
    }
}