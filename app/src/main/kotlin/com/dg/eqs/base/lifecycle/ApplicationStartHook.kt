package com.dg.eqs.base.lifecycle

import com.dg.eqs.base.purchasing.BillingService
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ApplicationStartHook(
        private val billingService: BillingService) {

    operator fun invoke() {
        GlobalScope.launch(Main) {
            billingService.consumePendingInAppPurchases()
        }
    }
}