package com.dg.eqs.base.extension

import com.android.billingclient.api.SkuDetails
import java.text.NumberFormat
import java.util.*


val SkuDetails.writtenOutPrice: String
    get() {
        val priceCurrency = Currency.getInstance(priceCurrencyCode)

        val priceFormat = NumberFormat.getInstance().apply {
            minimumFractionDigits = priceCurrency.defaultFractionDigits
            maximumFractionDigits = priceCurrency.defaultFractionDigits
        }

        return priceFormat.format(priceAmount) + " " + priceCurrency.displayName
    }

private val SkuDetails.priceAmount get() = priceAmountMicros / 1_000_000f