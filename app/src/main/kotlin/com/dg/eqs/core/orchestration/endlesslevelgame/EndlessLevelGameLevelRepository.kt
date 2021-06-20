package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.core.generation.generatedlevel.GeneratedLevelGenerator
import com.dg.eqs.core.orchestration.GameLevelRepository
import com.dg.eqs.core.orchestration.GameSteps
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase


class EndlessLevelGameLevelRepository(
        private val presetLevelDatabase: PresetLevelDatabase,
        private val generatedLevelDatabase: GeneratedLevelDatabase,
        private val generatedLevelGenerator: GeneratedLevelGenerator,
        private val endlessLevelGameTracking: EndlessLevelGameTracking
) : GameLevelRepository {

    override fun loadInitialLevel(): AnyLevel {
        val unfinishedLevel =
                loadFirstUnfinishedLevel()
                        ?: generateUnfinishedLevel()

        endlessLevelGameTracking
                .trackLevelLoaded(unfinishedLevel)

        return unfinishedLevel
    }

    override fun loadEnsuingLevel() = loadInitialLevel()

    override fun finishLevel(level: AnyLevel, steps: GameSteps) {
        endlessLevelGameTracking
                .trackLevelFinished(level)

        when(val finishedLevel = level.finish(steps)) {
            is PresetLevel -> presetLevelDatabase
                    .saveLevel(finishedLevel)

            is GeneratedLevel -> generatedLevelDatabase
                    .saveLevel(finishedLevel)
        }
    }

    private fun loadFirstUnfinishedLevel() =
            presetLevelDatabase.loadFirstUnfinishedLevel()
                    ?: generatedLevelDatabase.loadFirstUnfinishedLevel()

    private fun generateUnfinishedLevel(): AnyLevel {
        val generatedLevel = generatedLevelGenerator
                .generateUnfinishedLevel()

        val generatedKey = generatedLevelDatabase
                .saveLevel(generatedLevel)

        return generatedLevel.copy(levelKey = generatedKey)
    }

    private fun AnyLevel.finish(steps: GameSteps): AnyLevel {
        val newSteps = steps.validStepsCount

        return copy(finished = true,
                    gameSteps = newSteps,
                    bestSteps = newSteps)
    }
}