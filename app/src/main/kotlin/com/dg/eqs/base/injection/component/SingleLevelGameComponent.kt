package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.SingleLevelGameModule
import com.dg.eqs.core.orchestration.singlelevelgame.SingleLevelGameCommander
import dagger.Subcomponent


@Subcomponent(modules = [SingleLevelGameModule::class])
interface SingleLevelGameComponent {
    fun singleLevelGameCommander(): SingleLevelGameCommander

    @Subcomponent.Builder
    interface Builder {
        fun singleLevelGameModule(singleLevelGameModule: SingleLevelGameModule): Builder

        fun build(): SingleLevelGameComponent
    }
}