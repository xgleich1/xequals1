package com.dg.eqs.page.assist

import android.app.Activity
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.SkuDetails
import com.dg.eqs.R
import com.dg.eqs.base.enveloping.DrawableRes
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.base.observation.LiveDataCommandObserver
import com.dg.eqs.base.observation.LiveDataObserver
import com.dg.eqs.base.purchasing.BillingService
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFailed
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFinished
import com.dg.eqs.base.purchasing.result.PurchaseResult.*
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsPresent
import com.dg.eqs.util.rules.MainDispatcherRule
import org.mockito.kotlin.any
import org.mockito.kotlin.anyVararg
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AssistPageViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var billingService: BillingService

    @Mock
    private lateinit var showThankYouImageAction: (DrawableRes) -> Unit
    @Mock
    private lateinit var donateButtonTextAction: (StringRes) -> Unit
    @Mock
    private lateinit var donateButtonEnabledAction: (Boolean) -> Unit
    @Mock
    private lateinit var donateButtonLoadingAction: (Boolean) -> Unit
    @Mock
    private lateinit var donationStatusAction: (StringRes) -> Unit
    @Mock
    private lateinit var navigateBackAction: () -> Unit

    @Mock
    private lateinit var billingResult: BillingResult
    @Mock
    private lateinit var skuDetails: SkuDetails
    @Mock
    private lateinit var activity: Activity
    @Mock
    private lateinit var purchase: Purchase


    @Before
    fun setUp() {
        setupDonationPrice()
    }

    @Test
    fun `Should immediately load the sku details for the donation`() = runBlocking<Unit> {
        // GIVEN
        setupSkuDetailsPresent()
        createAndObserveViewModel()

        // THEN
        verify(billingService).loadInAppSkuDetails(
                R.string.assist_sku_donation_1)
    }

    @Test
    fun `Should hide the donate button loading once the sku details are loaded`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        createAndObserveViewModel()

        // THEN
        inOrder(billingService, donateButtonLoadingAction) {
            verify(billingService).loadInAppSkuDetails(anyVararg())

            verify(donateButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the donate button once the sku details are loaded`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        createAndObserveViewModel()

        // THEN
        inOrder(billingService, donateButtonEnabledAction) {
            verify(billingService).loadInAppSkuDetails(anyVararg())

            verify(donateButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the price of the donation once the sku details are loaded`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        createAndObserveViewModel()

        // THEN
        verify(donateButtonTextAction).invoke(
                StringRes(R.string.assist_button_donate, "2,50 Euro"))
    }

    @Test
    fun `Should emit the sku details loaded status once the sku details are loaded`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        createAndObserveViewModel()

        // THEN
        inOrder(billingService, donationStatusAction) {
            verify(billingService).loadInAppSkuDetails(anyVararg())

            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_sku_loaded))
        }
    }

    @Test
    fun `Should navigate back when back is pressed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackPressed()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should navigate back when the back button is clicked`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onBackButtonClicked()

        // THEN
        verify(navigateBackAction).invoke()
    }

    @Test
    fun `Should launch the purchase flow when the donate button is clicked`() = runBlocking<Unit> {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        verify(billingService).launchPurchaseFlow(activity, skuDetails)
    }

    @Test
    fun `Should consume pending purchases before launching the purchase flow`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService) {
            verify(billingService).consumePendingInAppPurchases()

            verify(billingService).launchPurchaseFlow(any(), any())
        }
    }

    @Test
    fun `Should consume the purchase when the purchase flow results in one`() = runBlocking<Unit> {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        verify(billingService).consumePurchase(purchase)
    }

    @Test
    fun `Should disable the donate button before pending purchases are consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(donateButtonEnabledAction, billingService) {
            verify(donateButtonEnabledAction).invoke(false)

            verify(billingService).consumePendingInAppPurchases()
        }
    }

    @Test
    fun `Should show the donate button loading before pending purchases are consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(donateButtonLoadingAction, billingService) {
            verify(donateButtonLoadingAction).invoke(true)

            verify(billingService).consumePendingInAppPurchases()
        }
    }

    @Test
    fun `Should emit the purchasing status before pending purchases are consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(donationStatusAction, billingService) {
            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_purchasing))

            verify(billingService).consumePendingInAppPurchases()
        }
    }

    @Test
    fun `Should emit the purchased status when a purchase is made and consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donationStatusAction) {
            verify(billingService).consumePurchase(any())

            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_purchased))
        }
    }

    @Test
    fun `Should show the thank-you image when a purchase is made and consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, showThankYouImageAction) {
            verify(billingService).consumePurchase(any())

            verify(showThankYouImageAction).invoke(
                    DrawableRes(R.drawable.thankyou))
        }
    }

    @Test
    fun `Should hide the donate button loading when a purchase is made and consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonLoadingAction) {
            verify(billingService).consumePurchase(any())

            verify(donateButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the donate button when a purchase is made and consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFinished()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonEnabledAction) {
            verify(billingService).consumePurchase(any())

            verify(donateButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the pending status when a purchase is made but not consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donationStatusAction) {
            verify(billingService).consumePurchase(any())

            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_pending))
        }
    }

    @Test
    fun `Should show the thank-you image when a purchase is made but not consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, showThankYouImageAction) {
            verify(billingService).consumePurchase(any())

            verify(showThankYouImageAction).invoke(
                    DrawableRes(R.drawable.thankyou))
        }
    }

    @Test
    fun `Should hide the donate button loading when a purchase is made but not consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonLoadingAction) {
            verify(billingService).consumePurchase(any())

            verify(donateButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the donate button when a purchase is made but not consumed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFinished()
        setupConsumeFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonEnabledAction) {
            verify(billingService).consumePurchase(any())

            verify(donateButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the sku loaded status when a purchase is canceled before it's made`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseCanceled()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donationStatusAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_sku_loaded))
        }
    }

    @Test
    fun `Should hide the donate button loading when a purchase is canceled before it's made`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseCanceled()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonLoadingAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donateButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the donate button when a purchase is canceled before it's made`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseCanceled()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonEnabledAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donateButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the pending status when there's still a purchase pending`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchasePending()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donationStatusAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_pending))
        }
    }

    @Test
    fun `Should hide the donate button loading when there's still a purchase pending`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchasePending()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonLoadingAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donateButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the donate button when there's still a purchase pending`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchasePending()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonEnabledAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donateButtonEnabledAction).invoke(true)
        }
    }

    @Test
    fun `Should emit the failed status when a purchase failed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donationStatusAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donationStatusAction).invoke(
                    StringRes(R.string.assist_donation_status_failed))
        }
    }

    @Test
    fun `Should hide the donate button loading when a purchase failed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonLoadingAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donateButtonLoadingAction).invoke(false)
        }
    }

    @Test
    fun `Should enable the donate button when a purchase failed`() = runBlocking {
        // GIVEN
        setupSkuDetailsPresent()
        setupPurchaseFailed()

        val viewModel = createAndObserveViewModel()

        // WHEN
        viewModel.onDonateButtonClicked(activity)

        // THEN
        inOrder(billingService, donateButtonEnabledAction) {
            verify(billingService).launchPurchaseFlow(any(), any())

            verify(donateButtonEnabledAction).invoke(true)
        }
    }

    private fun setupDonationPrice() {
        whenever(skuDetails.priceAmountMicros).thenReturn(2500000)
        whenever(skuDetails.priceCurrencyCode).thenReturn("EUR")
    }

    private suspend fun setupSkuDetailsPresent() = whenever(billingService
            .loadInAppSkuDetails(anyVararg()))
            .thenReturn(SkuDetailsPresent(listOf(skuDetails)))

    private suspend fun setupPurchaseFinished() = whenever(billingService
            .launchPurchaseFlow(any(), any()))
            .thenReturn(PurchaseFinished(listOf(purchase)))

    private suspend fun setupPurchaseCanceled() = whenever(billingService
            .launchPurchaseFlow(any(), any()))
            .thenReturn(PurchaseCanceled(billingResult))

    private suspend fun setupPurchasePending() = whenever(billingService
            .launchPurchaseFlow(any(), any()))
            .thenReturn(PurchasePending(billingResult))

    private suspend fun setupPurchaseFailed() = whenever(billingService
            .launchPurchaseFlow(any(), any()))
            .thenReturn(PurchaseFailed(billingResult))

    private suspend fun setupConsumeFinished() = whenever(billingService
            .consumePurchase(any()))
            .thenReturn(ConsumeFinished("purchase_token"))

    private suspend fun setupConsumeFailed() = whenever(billingService
            .consumePurchase(any()))
            .thenReturn(ConsumeFailed(billingResult))

    private fun createAndObserveViewModel(): AssistPageViewModel {
        val viewModel = AssistPageViewModel(billingService)

        viewModel.showThankYouImage.observeForever(
                LiveDataObserver(showThankYouImageAction))
        viewModel.donateButtonText.observeForever(
                LiveDataObserver(donateButtonTextAction))
        viewModel.donateButtonEnabled.observeForever(
                LiveDataObserver(donateButtonEnabledAction))
        viewModel.donateButtonLoading.observeForever(
                LiveDataObserver(donateButtonLoadingAction))
        viewModel.donationStatus.observeForever(
                LiveDataObserver(donationStatusAction))
        viewModel.navigateBack.observeForever(
                LiveDataCommandObserver(navigateBackAction))

        return viewModel
    }
}