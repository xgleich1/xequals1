package com.dg.eqs.page.assist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.Factory
import com.dg.eqs.base.purchasing.BillingService


class AssistPageViewModelFactory(
        private val billingService: BillingService
) : Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) =
            AssistPageViewModel(billingService) as T
}