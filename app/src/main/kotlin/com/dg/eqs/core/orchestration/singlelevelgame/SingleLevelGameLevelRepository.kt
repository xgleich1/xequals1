package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.core.orchestration.GameLevelRepository
import com.dg.eqs.core.orchestration.GameSteps
import com.dg.eqs.core.progression.Level.*
import com.dg.eqs.core.progression.LevelKey
import com.dg.eqs.core.progression.LevelKey.*
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase


class SingleLevelGameLevelRepository(
        private val singleLevelKey: LevelKey,
        private val eventLevelDatabase: EventLevelDatabase,
        private val presetLevelDatabase: PresetLevelDatabase,
        private val generatedLevelDatabase: GeneratedLevelDatabase,
        private val singleLevelGameTracking: SingleLevelGameTracking
) : GameLevelRepository {

    override fun loadInitialLevel(): AnyLevel {
        val singleLevel = when(singleLevelKey) {
            is EventLevelKey -> eventLevelDatabase
                    .loadLevel(singleLevelKey)

            is PresetLevelKey -> presetLevelDatabase
                    .loadLevel(singleLevelKey)

            is GeneratedLevelKey -> generatedLevelDatabase
                    .loadLevel(singleLevelKey)
        }

        singleLevelGameTracking
                .trackLevelLoaded(singleLevel)

        return singleLevel
    }

    override fun loadEnsuingLevel(): AnyLevel? = null

    override fun finishLevel(level: AnyLevel, steps: GameSteps) {
        singleLevelGameTracking
                .trackLevelFinished(level)

        when(val finishedLevel = level.finish(steps)) {
            is EventLevel -> eventLevelDatabase
                    .saveLevel(finishedLevel)

            is PresetLevel -> presetLevelDatabase
                    .saveLevel(finishedLevel)

            is GeneratedLevel -> generatedLevelDatabase
                    .saveLevel(finishedLevel)
        }
    }

    private fun AnyLevel.finish(steps: GameSteps) = copy(
            finished = true,
            gameSteps = steps.validStepsCount,
            bestSteps = decideNewBestSteps(steps))

    private fun AnyLevel.decideNewBestSteps(steps: GameSteps) =
            with(steps) {
                if(bestSteps == 0) return validStepsCount

                validStepsCount.coerceAtMost(bestSteps)
            }
}