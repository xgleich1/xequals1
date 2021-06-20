package com.dg.eqs.base.purchasing

import android.app.Activity
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.BillingResponseCode.*
import com.android.billingclient.api.BillingClient.SkuType.INAPP
import com.android.billingclient.api.Purchase.PurchasesResult
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFailed
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFinished
import com.dg.eqs.base.purchasing.result.PurchaseResult.*
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsMissing
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsPresent
import org.mockito.kotlin.*
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class BillingServiceTest {
    private companion object {
        private const val TEST_SKU_RES_ID = 1
        private const val TEST_PURCHASE_TOKEN = "test_token"
    }

    @Mock
    private lateinit var billingClientBuilder: BillingClientBuilder
    @Mock
    private lateinit var billingParamsBuilder: BillingParamsBuilder
    @Mock
    private lateinit var billingResultTracking: BillingResultTracking
    @InjectMocks
    private lateinit var billingService: BillingService

    @Mock
    private lateinit var billingClient: BillingClient
    @Mock
    private lateinit var billingResult: BillingResult
    @Mock
    private lateinit var skuDetails: SkuDetails
    @Mock
    private lateinit var activity: Activity
    @Mock
    private lateinit var purchase: Purchase

    @Mock
    private lateinit var inAppSkuDetailsParams: SkuDetailsParams
    @Mock
    private lateinit var billingFlowParams: BillingFlowParams
    @Mock
    private lateinit var consumeParams: ConsumeParams


    @Before
    fun setUp() {
        setupBuildBillingClient()
        setupBuildBillingParams()
    }

    //<editor-fold desc="Loading the in-app sku details">
    @Test
    fun `Should use the billing client to load the in-app sku details for the skus`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsPresent()

        // WHEN
        billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        verify(billingClient).querySkuDetailsAsync(eq(inAppSkuDetailsParams), any())
    }

    @Test
    fun `Should start the billing connection before loading the in-app sku details`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsPresent()

        // WHEN
        billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        inOrder(billingClient) {
            verify(billingClient).startConnection(any())

            verify(billingClient).querySkuDetailsAsync(any(), any())
        }
    }

    @Test
    fun `Should end the billing connection after loading the in-app sku details`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsPresent()

        // WHEN
        billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        inOrder(billingClient) {
            verify(billingClient).querySkuDetailsAsync(any(), any())

            verify(billingClient).endConnection()
        }
    }

    @Test
    fun `Should still complete the in-app sku details loading when the service disconnects`() = runBlocking {
        // GIVEN
        setupBillingServiceDisconnected()
        setupSkuDetailsMissing()

        // WHEN
        billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        verify(billingClient).querySkuDetailsAsync(any(), any())
    }

    @Test
    fun `Should track the result when the in-app sku details are present`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsPresent()

        // WHEN
        billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        verify(billingResultTracking).trackSkuDetailsResult(
                SkuDetailsPresent(listOf(skuDetails)))
    }

    @Test
    fun `Should return the in-app sku details when they are present`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsPresent()

        // WHEN
        val skuDetailsResult = billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        assertThat(skuDetailsResult).isEqualTo(SkuDetailsPresent(listOf(skuDetails)))
    }

    @Test
    fun `Should track the result when the in-app sku details are missing`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsMissing()

        // WHEN
        billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        verify(billingResultTracking).trackSkuDetailsResult(
                SkuDetailsMissing(billingResult))
    }

    @Test
    fun `Should return the billing result when the in-app sku details are missing`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupSkuDetailsMissing()

        // WHEN
        val skuDetailsResult = billingService.loadInAppSkuDetails(TEST_SKU_RES_ID)

        // THEN
        assertThat(skuDetailsResult).isEqualTo(SkuDetailsMissing(billingResult))
    }
    //</editor-fold>

    //<editor-fold desc="Launching the purchase flow">
    @Test
    fun `Should use the billing client to launch the billing flow to purchase the sku`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFinished()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        verify(billingClient).launchBillingFlow(activity, billingFlowParams)
    }

    @Test
    fun `Should start the billing connection before launching the billing flow to purchase the sku`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFinished()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        inOrder(billingClient) {
            verify(billingClient).startConnection(any())

            verify(billingClient).launchBillingFlow(any(), any())
        }
    }

    @Test
    fun `Should end the billing connection after launching the billing flow to purchase the sku`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFinished()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        inOrder(billingClient) {
            verify(billingClient).launchBillingFlow(any(), any())

            verify(billingClient).endConnection()
        }
    }

    @Test
    fun `Should still complete the purchasing flow when the billing service disconnects`() = runBlocking<Unit> {
        // GIVEN
        setupBillingServiceDisconnected()
        setupPurchaseFailed()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        verify(billingClient).launchBillingFlow(any(), any())
    }

    @Test
    fun `Should track the result when the purchase flow results in a finished purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFinished()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        verify(billingResultTracking).trackPurchaseResult(
                PurchaseFinished(listOf(purchase)))
    }

    @Test
    fun `Should return the purchase when the purchase flow results in a finished purchase`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFinished()

        // WHEN
        val purchaseResult = billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        assertThat(purchaseResult).isEqualTo(PurchaseFinished(listOf(purchase)))
    }

    @Test
    fun `Should track the result when the purchase flow results in a canceled purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseCanceled()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        verify(billingResultTracking).trackPurchaseResult(
                PurchaseCanceled(billingResult))
    }

    @Test
    fun `Should return the billing result when the purchase flow results in a canceled purchase`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseCanceled()

        // WHEN
        val purchaseResult = billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        assertThat(purchaseResult).isEqualTo(PurchaseCanceled(billingResult))
    }

    @Test
    fun `Should track the result when the purchase flow results in a pending purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchasePending()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        verify(billingResultTracking).trackPurchaseResult(
                PurchasePending(billingResult))
    }

    @Test
    fun `Should return the billing result when the purchase flow results in a pending purchase`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchasePending()

        // WHEN
        val purchaseResult = billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        assertThat(purchaseResult).isEqualTo(PurchasePending(billingResult))
    }

    @Test
    fun `Should track the result when the purchase flow results in a failed purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFailed()

        // WHEN
        billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        verify(billingResultTracking).trackPurchaseResult(
                PurchaseFailed(billingResult))
    }

    @Test
    fun `Should return the billing result when the purchase flow results in a failed purchase`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPurchaseFailed()

        // WHEN
        val purchaseResult = billingService.launchPurchaseFlow(activity, skuDetails)

        // THEN
        assertThat(purchaseResult).isEqualTo(PurchaseFailed(billingResult))
    }
    //</editor-fold>

    //<editor-fold desc="Consuming the purchase">
    @Test
    fun `Should use the billing client to consume the purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFinished()

        // WHEN
        billingService.consumePurchase(purchase)

        // THEN
        verify(billingClient).consumeAsync(eq(consumeParams), any())
    }

    @Test
    fun `Should start the billing connection before consuming the purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFinished()

        // WHEN
        billingService.consumePurchase(purchase)

        // THEN
        inOrder(billingClient) {
            verify(billingClient).startConnection(any())

            verify(billingClient).consumeAsync(any(), any())
        }
    }

    @Test
    fun `Should end the billing connection after consuming the purchase`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFinished()

        // WHEN
        billingService.consumePurchase(purchase)

        // THEN
        inOrder(billingClient) {
            verify(billingClient).consumeAsync(any(), any())

            verify(billingClient).endConnection()
        }
    }

    @Test
    fun `Should still complete consuming the purchase when the service disconnects`() = runBlocking {
        // GIVEN
        setupBillingServiceDisconnected()
        setupConsumeFailed()

        // WHEN
        billingService.consumePurchase(purchase)


        // THEN
        verify(billingClient).consumeAsync(any(), any())
    }

    @Test
    fun `Should track the result when consuming the purchase finishes`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFinished()

        // WHEN
        billingService.consumePurchase(purchase)

        // THEN
        verify(billingResultTracking).trackConsumeResult(
                ConsumeFinished(TEST_PURCHASE_TOKEN))
    }

    @Test
    fun `Should return the purchase token when consuming the purchase finishes`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFinished()

        // WHEN
        val consumeResult = billingService.consumePurchase(purchase)

        // THEN
        assertThat(consumeResult).isEqualTo(ConsumeFinished(TEST_PURCHASE_TOKEN))
    }

    @Test
    fun `Should track the result when consuming the purchase fails`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFailed()

        // WHEN
        billingService.consumePurchase(purchase)

        // THEN
        verify(billingResultTracking).trackConsumeResult(
                ConsumeFailed(billingResult))
    }

    @Test
    fun `Should return the billing result when consuming the purchase fails`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupConsumeFailed()

        // WHEN
        val consumeResult = billingService.consumePurchase(purchase)

        // THEN
        assertThat(consumeResult).isEqualTo(ConsumeFailed(billingResult))
    }
    //</editor-fold>

    //<editor-fold desc="Consuming pending in-app purchases">
    @Test
    fun `Should use the billing client to load pending in-app purchases`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPendingPurchases()
        setupConsumeFinished()

        // WHEN
        billingService.consumePendingInAppPurchases()

        // THEN
        verify(billingClient).queryPurchases(INAPP)
    }

    @Test
    fun `Should use the billing client to consume pending in-app purchases`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPendingPurchases()
        setupConsumeFinished()

        // WHEN
        billingService.consumePendingInAppPurchases()

        // THEN
        verify(billingClient).consumeAsync(eq(consumeParams), any())
    }

    @Test
    fun `Should start the billing connection before loading pending in-app purchases`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPendingPurchases()
        setupConsumeFinished()

        // WHEN
        billingService.consumePendingInAppPurchases()

        // THEN
        inOrder(billingClient) {
            verify(billingClient).startConnection(any())

            verify(billingClient).queryPurchases(any())
        }
    }

    @Test
    fun `Should end the billing connection after loading pending in-app purchases`() = runBlocking {
        // GIVEN
        setupBillingSetupFinished()
        setupPendingPurchases()
        setupConsumeFinished()

        // WHEN
        billingService.consumePendingInAppPurchases()

        // THEN
        inOrder(billingClient) {
            verify(billingClient).queryPurchases(any())

            verify(billingClient).endConnection()
        }
    }

    @Test
    fun `Should still complete the pending in-app purchases loading when the service disconnects`() = runBlocking<Unit> {
        // GIVEN
        setupBillingSetupFinished()
        setupPendingPurchases()
        setupConsumeFinished()

        // WHEN
        billingService.consumePendingInAppPurchases()

        // THEN
        verify(billingClient).queryPurchases(any())
    }
    //</editor-fold>

    private fun setupBuildBillingClient() {
        whenever(billingClientBuilder.build(anyOrNull())) doReturn billingClient
    }

    private fun setupBuildBillingParams() {
        whenever(billingParamsBuilder.buildInAppSkuDetailsParams(TEST_SKU_RES_ID)) doReturn inAppSkuDetailsParams
        whenever(billingParamsBuilder.buildBillingFlowParams(skuDetails)) doReturn billingFlowParams
        whenever(billingParamsBuilder.buildConsumeParams(purchase)) doReturn consumeParams
    }

    private fun setupBillingSetupFinished() =
            argumentCaptor<BillingClientStateListener>().apply {
                whenever(billingClient.startConnection(capture())) doAnswer {
                    lastValue.onBillingSetupFinished(billingResult)
                }
            }

    private fun setupBillingServiceDisconnected() =
            argumentCaptor<BillingClientStateListener>().apply {
                whenever(billingClient.startConnection(capture())) doAnswer {
                    lastValue.onBillingServiceDisconnected()
                }
            }

    private fun setupSkuDetailsPresent() =
            argumentCaptor<SkuDetailsResponseListener>().apply {
                whenever(billingClient.querySkuDetailsAsync(any(), capture())) doAnswer {
                    lastValue.onSkuDetailsResponse(billingResult, listOf(skuDetails))
                }
            }

    private fun setupSkuDetailsMissing() =
            argumentCaptor<SkuDetailsResponseListener>().apply {
                whenever(billingClient.querySkuDetailsAsync(any(), capture())) doAnswer {
                    lastValue.onSkuDetailsResponse(billingResult, emptyList())
                }
            }

    private fun setupPurchaseFinished() {
        whenever(billingResult.responseCode) doReturn OK

        argumentCaptor<((billingResult: BillingResult, purchases: List<Purchase>?) -> Unit)>().apply {
            whenever(billingClientBuilder.build(capture())) doAnswer {
                lastValue.invoke(billingResult, listOf(purchase))

                billingClient
            }
        }
    }

    private fun setupPurchaseCanceled() {
        whenever(billingResult.responseCode) doReturn USER_CANCELED

        argumentCaptor<((billingResult: BillingResult, purchases: List<Purchase>?) -> Unit)>().apply {
            whenever(billingClientBuilder.build(capture())) doAnswer {
                lastValue.invoke(billingResult, emptyList())

                billingClient
            }
        }
    }

    private fun setupPurchasePending() {
        whenever(billingResult.responseCode) doReturn ITEM_ALREADY_OWNED

        argumentCaptor<((billingResult: BillingResult, purchases: List<Purchase>?) -> Unit)>().apply {
            whenever(billingClientBuilder.build(capture())) doAnswer {
                lastValue.invoke(billingResult, emptyList())

                billingClient
            }
        }
    }

    private fun setupPurchaseFailed() {
        whenever(billingResult.responseCode) doReturn ERROR

        argumentCaptor<((billingResult: BillingResult, purchases: List<Purchase>?) -> Unit)>().apply {
            whenever(billingClientBuilder.build(capture())) doAnswer {
                lastValue.invoke(billingResult, emptyList())

                billingClient
            }
        }
    }

    private fun setupConsumeFinished() {
        whenever(billingResult.responseCode) doReturn OK

        argumentCaptor<ConsumeResponseListener>().apply {
            whenever(billingClient.consumeAsync(any(), capture())) doAnswer {
                lastValue.onConsumeResponse(billingResult, TEST_PURCHASE_TOKEN)
            }
        }
    }

    private fun setupConsumeFailed() {
        whenever(billingResult.responseCode) doReturn DEVELOPER_ERROR

        argumentCaptor<ConsumeResponseListener>().apply {
            whenever(billingClient.consumeAsync(any(), capture())) doAnswer {
                lastValue.onConsumeResponse(billingResult, TEST_PURCHASE_TOKEN)
            }
        }
    }

    private fun setupPendingPurchases() {
        whenever(billingClient.queryPurchases(any())) doReturn
                PurchasesResult(billingResult, listOf(purchase))
    }
}