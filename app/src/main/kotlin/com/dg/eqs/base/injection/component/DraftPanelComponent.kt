package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.DraftPanelModule
import com.dg.eqs.core.visualization.DraftPanel
import dagger.Subcomponent


@Subcomponent(modules = [DraftPanelModule::class])
interface DraftPanelComponent {
    fun inject(draftPanel: DraftPanel)

    @Subcomponent.Builder
    interface Builder {
        fun draftPanelModule(draftPanelModule: DraftPanelModule): Builder

        fun build(): DraftPanelComponent
    }
}