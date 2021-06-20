package com.dg.eqs.base.purchasing.result

import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase


sealed class PurchaseResult {
    data class PurchaseFinished(val purchases: List<Purchase>) : PurchaseResult()

    data class PurchaseCanceled(val billingResult: BillingResult) : PurchaseResult()

    data class PurchasePending(val billingResult: BillingResult) : PurchaseResult()

    data class PurchaseFailed(val billingResult: BillingResult) : PurchaseResult()
}