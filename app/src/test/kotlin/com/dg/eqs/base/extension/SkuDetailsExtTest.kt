package com.dg.eqs.base.extension

import com.android.billingclient.api.SkuDetails
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SkuDetailsExtTest {
    @Mock
    private lateinit var skuDetails: SkuDetails


    @Before
    fun setUp() {
        whenever(skuDetails.priceAmountMicros) doReturn 2500000
    }

    @Test
    fun `Should provide the written out price in a currency with zero fraction digits`() {
        // GIVEN
        whenever(skuDetails.priceCurrencyCode) doReturn "BIF"

        // THEN
        assertThat(skuDetails.writtenOutPrice).isEqualTo("2 Burundi-Franc")
    }

    @Test
    fun `Should provide the written out price for a currency with two fraction digits`() {
        // GIVEN
        whenever(skuDetails.priceCurrencyCode) doReturn "EUR"

        // THEN
        assertThat(skuDetails.writtenOutPrice).isEqualTo("2,50 Euro")
    }

    @Test
    fun `Should provide the written out price for a currency with three fraction digits`() {
        // GIVEN
        whenever(skuDetails.priceCurrencyCode) doReturn "BHD"

        // THEN
        assertThat(skuDetails.writtenOutPrice).isEqualTo("2,500 Bahrain-Dinar")
    }
}