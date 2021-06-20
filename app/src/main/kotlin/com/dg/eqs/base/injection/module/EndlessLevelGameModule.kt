package com.dg.eqs.base.injection.module

import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.generation.generatedlevel.GeneratedLevelGenerator
import com.dg.eqs.core.orchestration.endlesslevelgame.*
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import dagger.Module
import dagger.Provides


@Module
open class EndlessLevelGameModule {
    @Provides
    open fun provideEndlessLevelGameCommander(
            endlessLevelGameLevelRepository: EndlessLevelGameLevelRepository,
            endlessLevelGameInfoRepository: EndlessLevelGameInfoRepository,
            endlessLevelGameStatusRepository: EndlessLevelGameStatusRepository,
            intentionExecutor: IntentionExecutor
    ): EndlessLevelGameCommander {

        return EndlessLevelGameCommander(
                endlessLevelGameLevelRepository,
                endlessLevelGameInfoRepository,
                endlessLevelGameStatusRepository,
                intentionExecutor)
    }

    @Provides
    open fun provideEndlessLevelGameLevelRepository(
            presetLevelDatabase: PresetLevelDatabase,
            generatedLevelDatabase: GeneratedLevelDatabase,
            generatedLevelGenerator: GeneratedLevelGenerator,
            endlessLevelGameTracking: EndlessLevelGameTracking
    ): EndlessLevelGameLevelRepository {

        return EndlessLevelGameLevelRepository(
                presetLevelDatabase,
                generatedLevelDatabase,
                generatedLevelGenerator,
                endlessLevelGameTracking)
    }

    @Provides
    open fun provideEndlessLevelGameTracking(
            tracking: Tracking
    ): EndlessLevelGameTracking {

        return EndlessLevelGameTracking(tracking)
    }

    @Provides
    open fun provideEndlessLevelGameInfoRepository(
            offlinePersistence: OfflinePersistence
    ): EndlessLevelGameInfoRepository {

        return EndlessLevelGameInfoRepository(offlinePersistence)
    }

    @Provides
    open fun provideEndlessLevelGameStatusRepository(): EndlessLevelGameStatusRepository {
        return EndlessLevelGameStatusRepository()
    }
}