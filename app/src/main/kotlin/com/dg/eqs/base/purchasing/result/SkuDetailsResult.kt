package com.dg.eqs.base.purchasing.result

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.SkuDetails


sealed class SkuDetailsResult {
    data class SkuDetailsPresent(val skuDetails: List<SkuDetails>) : SkuDetailsResult()

    data class SkuDetailsMissing(val billingResult: BillingResult) : SkuDetailsResult()
}