package com.dg.eqs.base.injection.component

import com.dg.eqs.base.injection.module.LevelPageModule
import com.dg.eqs.page.level.LevelPage
import dagger.Subcomponent


@Subcomponent(modules = [LevelPageModule::class])
interface LevelPageComponent {
    fun inject(levelPage: LevelPage)

    @Subcomponent.Builder
    interface Builder {
        fun build(): LevelPageComponent
    }
}