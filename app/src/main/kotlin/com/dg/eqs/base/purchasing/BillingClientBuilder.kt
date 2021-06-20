package com.dg.eqs.base.purchasing

import android.app.Application
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase


class BillingClientBuilder(private val application: Application) {
    fun build(onPurchaseUpdated: ((
            billingResult: BillingResult,
            purchases: List<Purchase>?) -> Unit)? = null): BillingClient =
            BillingClient
                    .newBuilder(application)
                    .enablePendingPurchases()
                    .setListener { billingResult, purchases ->
                        onPurchaseUpdated?.invoke(billingResult, purchases)
                    }
                    .build()
}