package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyLevel


interface GameLevelRepository {
    fun loadInitialLevel(): AnyLevel

    fun loadEnsuingLevel(): AnyLevel?

    fun finishLevel(level: AnyLevel, steps: GameSteps)
}