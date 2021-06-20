package com.dg.eqs.base.injection.module

import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.page.level.LevelItemBuilder
import com.dg.eqs.page.level.LevelPageViewModelFactory
import dagger.Module
import dagger.Provides


@Module
open class LevelPageModule {
    @Provides
    open fun provideLevelPageViewModelFactory(
            generatedLevelDatabase: GeneratedLevelDatabase,
            presetLevelDatabase: PresetLevelDatabase,
            levelItemBuilder: LevelItemBuilder): LevelPageViewModelFactory {

        return LevelPageViewModelFactory(
                generatedLevelDatabase,
                presetLevelDatabase,
                levelItemBuilder)
    }

    @Provides
    open fun provideLevelItemBuilder(): LevelItemBuilder {
        return LevelItemBuilder()
    }
}