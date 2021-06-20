package com.dg.eqs.base.injection.module

import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import com.dg.eqs.page.offline.OfflinePageViewModelFactory
import dagger.Module
import dagger.Provides


@Module
open class OfflinePageModule {
    @Provides
    open fun provideOfflinePageViewModelFactory(
            presetLevelDatabase: PresetLevelDatabase,
            generatedLevelDatabase: GeneratedLevelDatabase): OfflinePageViewModelFactory {

        return OfflinePageViewModelFactory(
                presetLevelDatabase,
                generatedLevelDatabase)
    }
}