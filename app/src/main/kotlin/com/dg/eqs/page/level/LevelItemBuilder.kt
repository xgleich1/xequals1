package com.dg.eqs.page.level

import com.dg.eqs.R
import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.enveloping.StringRes


class LevelItemBuilder {
    fun buildLevelItem(level: AnyLevel) =
            LevelItem(level.exercise, level.steps)

    private val AnyLevel.steps
        get() = when {
            bestSteps == 0 -> StringRes(R.string.level_item_no_steps)
            bestSteps == 1 -> StringRes(R.string.level_item_one_step)
            bestSteps > 99 -> StringRes(R.string.level_item_many_steps)

            else -> StringRes(R.string.level_item_some_steps, bestSteps)
        }
}