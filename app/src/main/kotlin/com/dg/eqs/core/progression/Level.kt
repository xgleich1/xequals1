package com.dg.eqs.core.progression

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.progression.LevelKey.*


sealed class Level<LevelKeyType : LevelKey> {
    abstract val levelKey: LevelKeyType
    abstract val exercise: AnyOrigin
    abstract val finished: Boolean
    abstract val gameSteps: Int
    abstract val bestSteps: Int


    class EventLevel(
            override val levelKey: EventLevelKey,
            override val exercise: AnyOrigin,
            override val finished: Boolean,
            override val gameSteps: Int,
            override val bestSteps: Int) : Level<EventLevelKey>() {

        override fun copy(
                levelKey: EventLevelKey,
                exercise: AnyOrigin,
                finished: Boolean,
                gameSteps: Int,
                bestSteps: Int): EventLevel {

            return EventLevel(
                    levelKey,
                    exercise,
                    finished,
                    gameSteps,
                    bestSteps)
        }
    }

    class PresetLevel(
            override val levelKey: PresetLevelKey,
            override val exercise: AnyOrigin,
            override val finished: Boolean,
            override val gameSteps: Int,
            override val bestSteps: Int) : Level<PresetLevelKey>() {

        override fun copy(
                levelKey: PresetLevelKey,
                exercise: AnyOrigin,
                finished: Boolean,
                gameSteps: Int,
                bestSteps: Int): PresetLevel {

            return PresetLevel(
                    levelKey,
                    exercise,
                    finished,
                    gameSteps,
                    bestSteps)
        }
    }

    class GeneratedLevel(
            override val levelKey: GeneratedLevelKey,
            override val exercise: AnyOrigin,
            override val finished: Boolean,
            override val gameSteps: Int,
            override val bestSteps: Int) : Level<GeneratedLevelKey>() {

        override fun copy(
                levelKey: GeneratedLevelKey,
                exercise: AnyOrigin,
                finished: Boolean,
                gameSteps: Int,
                bestSteps: Int): GeneratedLevel {

            return GeneratedLevel(
                    levelKey,
                    exercise,
                    finished,
                    gameSteps,
                    bestSteps)
        }
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as AnyLevel

        if(levelKey != other.levelKey) return false
        if(exercise != other.exercise) return false
        if(finished != other.finished) return false
        if(gameSteps != other.gameSteps) return false
        if(bestSteps != other.bestSteps) return false

        return true
    }

    override fun hashCode(): Int {
        var result = levelKey.hashCode()

        result = 31 * result + exercise.hashCode()
        result = 31 * result + finished.hashCode()
        result = 31 * result + gameSteps
        result = 31 * result + bestSteps

        return result
    }

    override fun toString() = javaClass.simpleName +
            "($levelKey, $exercise, $finished, $gameSteps, $bestSteps)"

    abstract fun copy(
            levelKey: LevelKeyType = this.levelKey,
            exercise: AnyOrigin = this.exercise,
            finished: Boolean = this.finished,
            gameSteps: Int = this.gameSteps,
            bestSteps: Int = this.bestSteps): Level<LevelKeyType>
}