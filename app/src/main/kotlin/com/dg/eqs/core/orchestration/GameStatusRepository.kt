package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.enveloping.StringRes


interface GameStatusRepository {
    fun loadInitialStatus(level: AnyLevel): StringRes

    fun loadEnsuingStatus(level: AnyLevel, steps: GameSteps): StringRes

    fun loadCeasingStatus(level: AnyLevel, steps: GameSteps): StringRes
}