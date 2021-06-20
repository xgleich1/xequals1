package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.EndlessLevelGameModule
import com.dg.eqs.core.orchestration.endlesslevelgame.EndlessLevelGameCommander
import dagger.Subcomponent


@Subcomponent(modules = [EndlessLevelGameModule::class])
interface EndlessLevelGameComponent {
    fun endlessLevelGameCommander(): EndlessLevelGameCommander

    @Subcomponent.Builder
    interface Builder {
        fun build(): EndlessLevelGameComponent
    }
}