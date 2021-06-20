package com.dg.eqs.core.generation.generatedlevel

import com.dg.eqs.core.generation.generatedlevel.equalsrelation.EqualsRelationGenerator
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey


class GeneratedLevelGenerator(
        private val equalsRelationGenerator: EqualsRelationGenerator) {

    fun generateUnfinishedLevel(): GeneratedLevel {
        val exercise = equalsRelationGenerator.generateEqualsRelation()

        return GeneratedLevel(GeneratedLevelKey(0), exercise, false, 0 ,0)
    }
}