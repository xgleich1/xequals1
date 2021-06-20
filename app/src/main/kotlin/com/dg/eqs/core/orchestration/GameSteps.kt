package com.dg.eqs.core.orchestration

import com.dg.eqs.base.extension.last
import com.dg.eqs.core.execution.Step


class GameSteps(private val steps: List<Step>) : List<Step> by steps {
    val lastInfo get() = last.info
    val lastOrigin get() = last.origin

    val validStepsCount get() = count(Step::isValid)


    constructor(vararg steps: Step) : this(steps.toList())

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as GameSteps

        if(steps != other.steps) return false

        return true
    }

    override fun hashCode() = steps.hashCode()

    override fun toString() = steps.toString()

    operator fun plus(step: Step) = GameSteps(steps + step)

    fun revert(): GameSteps {
        if(validStepsCount == 0) return GameSteps()

        return GameSteps(dropLastWhile(Step::isInvalid)
                .dropLast(1)
                .dropLastWhile(Step::isInvalid))
    }
}