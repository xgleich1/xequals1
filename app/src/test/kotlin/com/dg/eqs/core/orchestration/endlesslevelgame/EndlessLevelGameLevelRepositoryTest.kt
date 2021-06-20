package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.generation.generatedlevel.GeneratedLevelGenerator
import com.dg.eqs.core.orchestration.GameSteps
import com.dg.eqs.core.progression.Level.GeneratedLevel
import com.dg.eqs.core.progression.Level.PresetLevel
import com.dg.eqs.core.progression.LevelKey.GeneratedLevelKey
import com.dg.eqs.core.progression.LevelKey.PresetLevelKey
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EndlessLevelGameLevelRepositoryTest {
    @Mock
    private lateinit var presetLevelDatabase: PresetLevelDatabase
    @Mock
    private lateinit var generatedLevelDatabase: GeneratedLevelDatabase
    @Mock
    private lateinit var generatedLevelGenerator: GeneratedLevelGenerator
    @Mock
    private lateinit var endlessLevelGameTracking: EndlessLevelGameTracking
    @InjectMocks
    private lateinit var repository: EndlessLevelGameLevelRepository

    @Mock
    private lateinit var exerciseA: AnyOrigin
    @Mock
    private lateinit var exerciseB: AnyOrigin
    @Mock
    private lateinit var exerciseC: AnyOrigin


    @Test
    fun `Should load the level for an endless level game`() {
        // GIVEN
        setupPresetLevelLoading(
                PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 0))

        setupGeneratedLevelLoading(
                GeneratedLevel(GeneratedLevelKey(1), exerciseB, false, 0, 0))

        setupGeneratedLevelGeneration(
                GeneratedLevel(GeneratedLevelKey(0), exerciseC, false, 0, 0))

        // WHEN
        val firstLevel = repository.loadInitialLevel()
        val secondLevel = repository.loadEnsuingLevel()
        val thirdLevel = repository.loadEnsuingLevel()

        // THEN
        assertThat(firstLevel).isEqualTo(
                PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 0))

        assertThat(secondLevel).isEqualTo(
                GeneratedLevel(GeneratedLevelKey(1), exerciseB, false, 0, 0))

        assertThat(thirdLevel).isEqualTo(
                GeneratedLevel(GeneratedLevelKey(2), exerciseC, false, 0, 0))
    }

    @Test
    fun `Should track when a preset level is loaded as the initial for a endless level game`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 0)

        setupPresetLevelLoading(level)

        // WHEN
        repository.loadInitialLevel()

        // THEN
        verify(endlessLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should track when a generated level is loaded as the initial for a endless level game`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseB, false, 0, 0)

        setupGeneratedLevelLoading(level)

        // WHEN
        repository.loadInitialLevel()

        // THEN
        verify(endlessLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should track when a generated level is generated as the initial for a endless level game`() {
        // GIVEN
        setupGeneratedLevelGeneration(
                GeneratedLevel(GeneratedLevelKey(0), exerciseC, false, 0, 0))

        // WHEN
        repository.loadInitialLevel()

        // THEN
        verify(endlessLevelGameTracking).trackLevelLoaded(
                GeneratedLevel(GeneratedLevelKey(2), exerciseC, false, 0, 0))
    }

    @Test
    fun `Should track when a preset level is loaded as the ensuing for a endless level game`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 0)

        setupPresetLevelLoading(level)

        // WHEN
        repository.loadEnsuingLevel()

        // THEN
        verify(endlessLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should track when a generated level is loaded as the ensuing for a endless level game`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseB, false, 0, 0)

        setupGeneratedLevelLoading(level)

        // WHEN
        repository.loadEnsuingLevel()

        // THEN
        verify(endlessLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should track when a generated level is generated as the ensuing for a endless level game`() {
        // GIVEN
        setupGeneratedLevelGeneration(
                GeneratedLevel(GeneratedLevelKey(0), exerciseC, false, 0, 0))

        // WHEN
        repository.loadEnsuingLevel()

        // THEN
        verify(endlessLevelGameTracking).trackLevelLoaded(
                GeneratedLevel(GeneratedLevelKey(2), exerciseC, false, 0, 0))
    }

    @Test
    fun `Should finish a previously loaded preset level`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(presetLevelDatabase).saveLevel(
                PresetLevel(PresetLevelKey(1), exerciseA, true, 1, 1))
    }

    @Test
    fun `Should finish a previously loaded generated level`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(generatedLevelDatabase).saveLevel(
                GeneratedLevel(GeneratedLevelKey(1), exerciseA, true, 1, 1))
    }

    @Test
    fun `Should track when a preset level is finished in a endless level game`() {
        // GIVEN
        val level = PresetLevel(PresetLevelKey(1), exerciseA, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(endlessLevelGameTracking).trackLevelFinished(level)
    }

    @Test
    fun `Should track when a generated level is finished in a endless level game`() {
        // GIVEN
        val level = GeneratedLevel(GeneratedLevelKey(1), exerciseA, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(endlessLevelGameTracking).trackLevelFinished(level)
    }

    private fun setupPresetLevelLoading(level: PresetLevel) {
        whenever(presetLevelDatabase.loadFirstUnfinishedLevel()).thenReturn(level, null)
    }

    private fun setupGeneratedLevelLoading(level: GeneratedLevel) {
        whenever(generatedLevelDatabase.loadFirstUnfinishedLevel()).thenReturn(level, null)
    }

    private fun setupGeneratedLevelGeneration(level: GeneratedLevel) {
        whenever(generatedLevelGenerator.generateUnfinishedLevel()).thenReturn(level)

        whenever(generatedLevelDatabase.saveLevel(level)) doReturn GeneratedLevelKey(2)
    }
}