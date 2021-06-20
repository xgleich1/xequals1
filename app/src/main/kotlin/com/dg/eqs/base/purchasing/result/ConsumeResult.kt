package com.dg.eqs.base.purchasing.result

import com.android.billingclient.api.BillingResult


sealed class ConsumeResult {
    data class ConsumeFinished(val purchaseToken: String) : ConsumeResult()

    data class ConsumeFailed(val billingResult: BillingResult) : ConsumeResult()
}