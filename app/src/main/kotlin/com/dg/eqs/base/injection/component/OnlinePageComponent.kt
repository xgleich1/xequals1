package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.OnlinePageModule
import com.dg.eqs.page.online.OnlinePage
import dagger.Subcomponent


@Subcomponent(modules = [OnlinePageModule::class])
interface OnlinePageComponent {
    fun inject(onlinePage: OnlinePage)

    @Subcomponent.Builder
    interface Builder {
        fun build(): OnlinePageComponent
    }
}