package com.dg.eqs.base.purchasing

import com.android.billingclient.api.BillingClient.BillingResponseCode.*
import com.android.billingclient.api.BillingResult
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFailed
import com.dg.eqs.base.purchasing.result.ConsumeResult.ConsumeFinished
import com.dg.eqs.base.purchasing.result.PurchaseResult.*
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsMissing
import com.dg.eqs.base.purchasing.result.SkuDetailsResult.SkuDetailsPresent
import com.dg.eqs.base.tracking.Tracking
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class BillingResultTrackingTest {
    @Mock
    private lateinit var tracking: Tracking
    @InjectMocks
    private lateinit var billingResultTracking: BillingResultTracking


    @Test
    fun `Should track an event when sku details are present`() {
        // WHEN
        billingResultTracking.trackSkuDetailsResult(
                SkuDetailsPresent(mock()))

        // THEN
        verify(tracking).trackEvent("sku_details_present")
    }

    @Test
    fun `Should track an event when sku details are missing`() {
        // GIVEN
        val billingResult: BillingResult = mock {
            on { responseCode } doReturn ERROR
        }

        // WHEN
        billingResultTracking.trackSkuDetailsResult(
                SkuDetailsMissing(billingResult))

        // THEN
        verify(tracking).trackEvent("sku_details_missing_error")
    }

    @Test
    fun `Should track an event when a purchase is finished`() {
        // WHEN
        billingResultTracking.trackPurchaseResult(
                PurchaseFinished(mock()))

        // THEN
        verify(tracking).trackEvent("purchase_finished")
    }

    @Test
    fun `Should track an event when a purchase is canceled`() {
        // GIVEN
        val billingResult: BillingResult = mock {
            on { responseCode } doReturn USER_CANCELED
        }

        // WHEN
        billingResultTracking.trackPurchaseResult(
                PurchaseCanceled(billingResult))

        // THEN
        verify(tracking).trackEvent("purchase_canceled_user_canceled")
    }

    @Test
    fun `Should track an event when a purchase is pending`() {
        // GIVEN
        val billingResult: BillingResult = mock {
            on { responseCode } doReturn DEVELOPER_ERROR
        }

        // WHEN
        billingResultTracking.trackPurchaseResult(
                PurchasePending(billingResult))

        // THEN
        verify(tracking).trackEvent("purchase_pending_developer_error")
    }

    @Test
    fun `Should track an event when a purchase failed`() {
        // GIVEN
        val billingResult: BillingResult = mock {
            on { responseCode } doReturn ERROR
        }

        // WHEN
        billingResultTracking.trackPurchaseResult(
                PurchaseFailed(billingResult))

        // THEN
        verify(tracking).trackEvent("purchase_failed_error")
    }

    @Test
    fun `Should track an event when consuming a purchase finished`() {
        // WHEN
        billingResultTracking.trackConsumeResult(
                ConsumeFinished("purchase_token"))

        // THEN
        verify(tracking).trackEvent("consume_finished")
    }

    @Test
    fun `Should track an event when consuming a purchase failed`() {
        // GIVEN
        val billingResult: BillingResult = mock {
            on { responseCode } doReturn ERROR
        }

        // WHEN
        billingResultTracking.trackConsumeResult(
                ConsumeFailed(billingResult))

        // THEN
        verify(tracking).trackEvent("consume_failed_error")
    }
}