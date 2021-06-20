package com.dg.eqs.base.injection.module

import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.orchestration.singlelevelgame.*
import com.dg.eqs.core.progression.LevelKey
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import dagger.Module
import dagger.Provides


@Module
open class SingleLevelGameModule(private val levelKey: LevelKey) {
    @Provides
    open fun provideSingleLevelGameCommander(
            singleLevelGameLevelRepository: SingleLevelGameLevelRepository,
            singleLevelGameInfoRepository: SingleLevelGameInfoRepository,
            singleLevelGameStatusRepository: SingleLevelGameStatusRepository,
            intentionExecutor: IntentionExecutor
    ): SingleLevelGameCommander {

        return SingleLevelGameCommander(
                singleLevelGameLevelRepository,
                singleLevelGameInfoRepository,
                singleLevelGameStatusRepository,
                intentionExecutor)
    }

    @Provides
    open fun provideSingleLevelGameLevelRepository(
            levelKey: LevelKey,
            eventLevelDatabase: EventLevelDatabase,
            presetLevelDatabase: PresetLevelDatabase,
            generatedLevelDatabase: GeneratedLevelDatabase,
            singleLevelGameTracking: SingleLevelGameTracking
    ): SingleLevelGameLevelRepository {

        return SingleLevelGameLevelRepository(
                levelKey,
                eventLevelDatabase,
                presetLevelDatabase,
                generatedLevelDatabase,
                singleLevelGameTracking)
    }

    @Provides
    open fun provideLevelKey(): LevelKey {
        return levelKey
    }

    @Provides
    open fun provideSingleLevelGameTracking(
            tracking: Tracking
    ): SingleLevelGameTracking {

        return SingleLevelGameTracking(tracking)
    }

    @Provides
    open fun provideSingleLevelGameInfoRepository(): SingleLevelGameInfoRepository {
        return SingleLevelGameInfoRepository()
    }

    @Provides
    open fun provideSingleLevelGameStatusRepository(): SingleLevelGameStatusRepository {
        return SingleLevelGameStatusRepository()
    }
}