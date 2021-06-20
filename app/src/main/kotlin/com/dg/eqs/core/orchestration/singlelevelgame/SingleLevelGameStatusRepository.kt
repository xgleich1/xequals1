package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.enveloping.StringRes
import com.dg.eqs.core.orchestration.GameStatusRepository
import com.dg.eqs.core.orchestration.GameSteps


class SingleLevelGameStatusRepository : GameStatusRepository {
    override fun loadInitialStatus(level: AnyLevel): StringRes {
        val initialSteps = level.bestSteps

        return when {
            initialSteps == 0 -> StringRes(
                    R.string.game_initial_status_no_steps)

            initialSteps == 1 -> StringRes(
                    R.string.game_initial_status_one_step)

            initialSteps > 99 -> StringRes(
                    R.string.game_initial_status_many_steps)

            else -> StringRes(
                    R.string.game_initial_status_some_steps, initialSteps)
        }
    }

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
        val initialSteps = level.bestSteps
        val ceasingSteps = steps.validStepsCount

        val stepsDifference = ceasingSteps - initialSteps

        return when {
            initialSteps == 0 ->
                loadCeasingStatusForFirstSteps(ceasingSteps)

            stepsDifference < 0 ->
                loadCeasingStatusForLessSteps(-stepsDifference)

            stepsDifference > 0 ->
                loadCeasingStatusForMoreSteps(stepsDifference)

            else -> StringRes(
                    R.string.game_ceasing_status_equal_steps)
        }
    }

    private fun loadCeasingStatusForFirstSteps(firstSteps: Int) = when {
        firstSteps == 1 -> StringRes(
                R.string.game_ceasing_status_one_step)

        firstSteps > 99 -> StringRes(
                R.string.game_ceasing_status_many_steps)

        else -> StringRes(
                R.string.game_ceasing_status_some_steps, firstSteps)
    }

    private fun loadCeasingStatusForLessSteps(lessSteps: Int) = when {
        lessSteps == 1 -> StringRes(
                R.string.game_ceasing_status_one_step_less)

        lessSteps > 99 -> StringRes(
                R.string.game_ceasing_status_many_steps_less)

        else -> StringRes(
                R.string.game_ceasing_status_some_steps_less, lessSteps)
    }

    private fun loadCeasingStatusForMoreSteps(moreSteps: Int) = when {
        moreSteps == 1 -> StringRes(
                R.string.game_ceasing_status_one_step_more)

        moreSteps > 99 -> StringRes(
                R.string.game_ceasing_status_many_steps_more)

        else -> StringRes(
                R.string.game_ceasing_status_some_steps_more, moreSteps)
    }
}