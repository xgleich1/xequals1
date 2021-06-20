package com.dg.eqs.core.generation.generatedlevel

import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.generation.generatedlevel.equalsrelation.EqualsRelationGenerator
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GeneratedLevelGeneratorTest {
    @Mock
    private lateinit var equalsRelationGenerator: EqualsRelationGenerator
    @InjectMocks
    private lateinit var generatedLevelGenerator: GeneratedLevelGenerator

    @Mock
    private lateinit var equalsRelation: EqualsRelation


    @Before
    fun setUp() {
        whenever(equalsRelationGenerator.generateEqualsRelation()) doReturn equalsRelation
    }

    @Test
    fun `Should generate an unfinished level with an equals relation as its exercise`() {
        // WHEN
        val generatedLevel = generatedLevelGenerator.generateUnfinishedLevel()

        // THEN
        val expectedLevel = GeneratedLevel(GeneratedLevelKey(0), equalsRelation, false, 0, 0)

        assertThat(generatedLevel).isEqualTo(expectedLevel)
    }
}