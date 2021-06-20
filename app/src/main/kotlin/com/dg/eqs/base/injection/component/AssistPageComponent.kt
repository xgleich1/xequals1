package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.AssistPageModule
import com.dg.eqs.page.assist.AssistPage
import dagger.Subcomponent


@Subcomponent(modules = [AssistPageModule::class])
interface AssistPageComponent {
    fun inject(assistPage: AssistPage)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AssistPageComponent
    }
}