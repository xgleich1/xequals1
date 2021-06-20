package com.dg.eqs.page.assist

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.SkuDetails
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.DrawableRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.extension.single
import com.dg.eqs.base.extension.writtenOutPrice
import com.dg.eqs.base.observation.LiveDataCommand
import com.dg.eqs.base.observation.emit
import com.dg.eqs.base.purchasing.BillingService
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFailed
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFinished
import com.dg.eqs.base.purchasing.result.PurchaseResult.*
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsMissing
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsPresent
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch


class AssistPageViewModel(
        private val billingService: BillingService
) : ViewModel() {

    val showThankYouImage = MutableLiveData<DrawableRes>()
    val donateButtonText = MutableLiveData<StringRes>()
    val donateButtonEnabled = MutableLiveData<Boolean>()
    val donateButtonLoading = MutableLiveData<Boolean>()
    val donationStatus = MutableLiveData<StringRes>()
    val navigateBack = MutableLiveData<LiveDataCommand>()

    private lateinit var donationSkuDetails: SkuDetails


    init {
        viewModelScope.launch(Main) {
            loadDonationSkuDetails()
        }
    }

    fun onBackPressed() = emitNavigateBack()

    fun onBackButtonClicked() = emitNavigateBack()

    fun onDonateButtonClicked(activity: Activity) {
        emitDisableDonateButton()

        showDonateButtonLoading()

        emitPurchasingStatus()

        viewModelScope.launch(Main) {
            consumePendingPurchases()

            launchPurchaseFlow(activity)

            hideDonateButtonLoading()

            emitEnableDonateButton()
        }
    }

    private suspend fun loadDonationSkuDetails() {
        val skuDetailsResult = billingService
                .loadInAppSkuDetails(R.string.assist_sku_donation_1)

        return when(skuDetailsResult) {
            is SkuDetailsPresent -> {
                donationSkuDetails = skuDetailsResult.skuDetails.single

                hideDonateButtonLoading()

                emitEnableDonateButton()

                emitDonationPricing()

                emitSkuLoadedStatus()
            }
            is SkuDetailsMissing -> Unit
        }
    }

    private suspend fun consumePendingPurchases() = billingService
            .consumePendingInAppPurchases()

    private suspend fun launchPurchaseFlow(activity: Activity) {
        val purchaseResult = billingService
                .launchPurchaseFlow(activity, donationSkuDetails)

        return when(purchaseResult) {
            is PurchaseFinished -> {
                val consumeResult = billingService
                        .consumePurchase(purchaseResult.purchases.single)

                when(consumeResult) {
                    is ConsumeFinished -> emitPurchasedStatus()
                    is ConsumeFailed -> emitPendingStatus()
                }

                showThankYouImage()
            }
            is PurchaseCanceled -> emitSkuLoadedStatus()
            is PurchasePending -> emitPendingStatus()
            is PurchaseFailed -> emitFailedStatus()
        }
    }

    private fun showThankYouImage() = showThankYouImage.emit(
            DrawableRes(R.drawable.thankyou))

    private fun emitDonationPricing() = donateButtonText.emit(
            StringRes(R.string.assist_button_donate,
                    donationSkuDetails.writtenOutPrice))

    private fun emitEnableDonateButton() = donateButtonEnabled.emit(true)

    private fun emitDisableDonateButton() = donateButtonEnabled.emit(false)

    private fun showDonateButtonLoading() = donateButtonLoading.emit(true)

    private fun hideDonateButtonLoading() = donateButtonLoading.emit(false)

    private fun emitSkuLoadedStatus() = donationStatus.emit(
            StringRes(R.string.assist_donation_status_sku_loaded))

    private fun emitPurchasingStatus() = donationStatus.emit(
            StringRes(R.string.assist_donation_status_purchasing))

    private fun emitPurchasedStatus() = donationStatus.emit(
            StringRes(R.string.assist_donation_status_purchased))

    private fun emitPendingStatus() = donationStatus.emit(
            StringRes(R.string.assist_donation_status_pending))

    private fun emitFailedStatus() = donationStatus.emit(
            StringRes(R.string.assist_donation_status_failed))

    private fun emitNavigateBack() = navigateBack.emit()
}