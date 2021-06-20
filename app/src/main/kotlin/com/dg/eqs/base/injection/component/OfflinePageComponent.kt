package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.OfflinePageModule
import com.dg.eqs.page.offline.OfflinePage
import dagger.Subcomponent


@Subcomponent(modules = [OfflinePageModule::class])
interface OfflinePageComponent {
    fun inject(offlinePage: OfflinePage)

    @Subcomponent.Builder
    interface Builder {
        fun build(): OfflinePageComponent
    }
}