package com.dg.eqs.base.injection.module

import com.dg.eqs.base.purchasing.BillingService
import com.dg.eqs.page.assist.AssistPageViewModelFactory
import dagger.Module
import dagger.Provides


@Module
open class AssistPageModule {
    @Provides
    open fun provideAssistPageViewModelFactory(
            billingService: BillingService): AssistPageViewModelFactory {

        return AssistPageViewModelFactory(billingService)
    }
}