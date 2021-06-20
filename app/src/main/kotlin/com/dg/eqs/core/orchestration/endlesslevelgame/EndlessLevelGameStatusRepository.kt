package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.core.orchestration.GameStatusRepository
import com.dg.eqs.core.orchestration.GameSteps


class EndlessLevelGameStatusRepository : GameStatusRepository {
    override fun loadInitialStatus(level: AnyLevel) =
            StringRes(R.string.game_initial_status_no_steps)

    override fun loadEnsuingStatus(level: AnyLevel, steps: GameSteps): StringRes {
        val ensuingSteps = steps.validStepsCount

        return when {
            ensuingSteps == 0 -> loadInitialStatus(level)

            ensuingSteps > 99 -> StringRes(
                    R.string.game_ensuing_status_many_steps)

            else -> StringRes(
                    R.string.game_ensuing_status_some_steps, ensuingSteps)
        }
    }

    override fun loadCeasingStatus(level: AnyLevel, steps: GameSteps): StringRes {
        val ceasingSteps = steps.validStepsCount

        return when {
            ceasingSteps == 1 -> StringRes(
                    R.string.game_ceasing_status_one_step)

            ceasingSteps > 99 -> StringRes(
                    R.string.game_ceasing_status_many_steps)

            else -> StringRes(
                    R.string.game_ceasing_status_some_steps, ceasingSteps)
        }
    }
}