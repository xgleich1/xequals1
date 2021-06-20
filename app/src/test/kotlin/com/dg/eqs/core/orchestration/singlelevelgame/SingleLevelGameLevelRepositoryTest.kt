package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.orchestration.GameSteps
import com.dg.eqs.core.progression.Level.*
import com.dg.eqs.core.progression.LevelKey
import com.dg.eqs.core.progression.LevelKey.*
import com.dg.eqs.core.progression.eventlevel.EventLevelDatabase
import com.dg.eqs.core.progression.generatedlevel.GeneratedLevelDatabase
import com.dg.eqs.core.progression.presetlevel.PresetLevelDatabase
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class SingleLevelGameLevelRepositoryTest {
    @Mock
    private lateinit var eventLevelDatabase: EventLevelDatabase
    @Mock
    private lateinit var presetLevelDatabase: PresetLevelDatabase
    @Mock
    private lateinit var generatedLevelDatabase: GeneratedLevelDatabase
    @Mock
    private lateinit var singleLevelGameTracking: SingleLevelGameTracking

    @Mock
    private lateinit var eventLevelKey: EventLevelKey
    @Mock
    private lateinit var presetLevelKey: PresetLevelKey
    @Mock
    private lateinit var generatedLevelKey: GeneratedLevelKey

    @Mock
    private lateinit var exercise: AnyOrigin


    @Test
    fun `Should load the level for a single event level game`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, true, 3, 2)

        setupEventLevelLoading(level)

        val repository = createRepository(eventLevelKey)

        // WHEN
        val firstLevel = repository.loadInitialLevel()
        val secondLevel = repository.loadEnsuingLevel()

        // THEN
        assertThat(firstLevel).isEqualTo(level)
        assertThat(secondLevel).isNull()
    }

    @Test
    fun `Should load the level for a single preset level game`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, true, 3, 2)

        setupPresetLevelLoading(level)

        val repository = createRepository(presetLevelKey)

        // WHEN
        val firstLevel = repository.loadInitialLevel()
        val secondLevel = repository.loadEnsuingLevel()

        // THEN
        assertThat(firstLevel).isEqualTo(level)
        assertThat(secondLevel).isNull()
    }

    @Test
    fun `Should load the level for a single generated level game`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, true, 3, 2)

        setupGeneratedLevelLoading(level)

        val repository = createRepository(generatedLevelKey)

        // WHEN
        val firstLevel = repository.loadInitialLevel()
        val secondLevel = repository.loadEnsuingLevel()

        // THEN
        assertThat(firstLevel).isEqualTo(level)
        assertThat(secondLevel).isNull()
    }

    @Test
    fun `Should track when a event level is loaded for a single level game`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, true, 3, 2)

        setupEventLevelLoading(level)

        val repository = createRepository(eventLevelKey)

        // WHEN
        repository.loadInitialLevel()

        // THEN
        verify(singleLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should track when a preset level is loaded for a single level game`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, true, 3, 2)

        setupPresetLevelLoading(level)

        val repository = createRepository(presetLevelKey)

        // WHEN
        repository.loadInitialLevel()

        // THEN
        verify(singleLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should track when a generated level is loaded for a single level game`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, true, 3, 2)

        setupGeneratedLevelLoading(level)

        val repository = createRepository(generatedLevelKey)

        // WHEN
        repository.loadInitialLevel()

        // THEN
        verify(singleLevelGameTracking).trackLevelLoaded(level)
    }

    @Test
    fun `Should finish a previously loaded unfinished event level`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        val repository = createRepository(eventLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(eventLevelDatabase).saveLevel(
                EventLevel(eventLevelKey, exercise, true, 1, 1))
    }

    @Test
    fun `Should finish a previously loaded unfinished preset level`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        val repository = createRepository(presetLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(presetLevelDatabase).saveLevel(
                PresetLevel(presetLevelKey, exercise, true, 1, 1))
    }

    @Test
    fun `Should finish a previously loaded unfinished generated level`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        val repository = createRepository(generatedLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(generatedLevelDatabase).saveLevel(
                GeneratedLevel(generatedLevelKey, exercise, true, 1, 1))
    }

    @Test
    fun `Should finish a previously loaded event level finished in less steps`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, true, 4, 3)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 2 }

        val repository = createRepository(eventLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(eventLevelDatabase).saveLevel(
                EventLevel(eventLevelKey, exercise, true, 2, 2))
    }

    @Test
    fun `Should finish a previously loaded preset level finished in less steps`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, true, 4, 3)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 2 }

        val repository = createRepository(presetLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(presetLevelDatabase).saveLevel(
                PresetLevel(presetLevelKey, exercise, true, 2, 2))
    }

    @Test
    fun `Should finish a previously loaded generated level finished in less steps`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, true, 4, 3)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 2 }

        val repository = createRepository(generatedLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(generatedLevelDatabase).saveLevel(
                GeneratedLevel(generatedLevelKey, exercise, true, 2, 2))
    }

    @Test
    fun `Should finish a previously loaded event level finished in more steps`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, true, 3, 2)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 4 }

        val repository = createRepository(eventLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(eventLevelDatabase).saveLevel(
                EventLevel(eventLevelKey, exercise, true, 4, 2))
    }

    @Test
    fun `Should finish a previously loaded preset level finished in more steps`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, true, 3, 2)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 4 }

        val repository = createRepository(presetLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(presetLevelDatabase).saveLevel(
                PresetLevel(presetLevelKey, exercise, true, 4, 2))
    }

    @Test
    fun `Should finish a previously loaded generated level finished in more steps`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, true, 3, 2)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 4 }

        val repository = createRepository(generatedLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(generatedLevelDatabase).saveLevel(
                GeneratedLevel(generatedLevelKey, exercise, true, 4, 2))
    }

    @Test
    fun `Should finish a previously loaded event level finished in equal steps`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, true, 3, 2)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 2 }

        val repository = createRepository(eventLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(eventLevelDatabase).saveLevel(
                EventLevel(eventLevelKey, exercise, true, 2, 2))
    }

    @Test
    fun `Should finish a previously loaded preset level finished in equal steps`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, true, 3, 2)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 2 }

        val repository = createRepository(presetLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(presetLevelDatabase).saveLevel(
                PresetLevel(presetLevelKey, exercise, true, 2, 2))
    }

    @Test
    fun `Should finish a previously loaded generated level finished in equal steps`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, true, 3, 2)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 2 }

        val repository = createRepository(generatedLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(generatedLevelDatabase).saveLevel(
                GeneratedLevel(generatedLevelKey, exercise, true, 2, 2))
    }

    @Test
    fun `Should track when a event level is finished in a single level game`() {
        // GIVEN
        val level = EventLevel(eventLevelKey, exercise, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        val repository = createRepository(eventLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(singleLevelGameTracking).trackLevelFinished(level)
    }

    @Test
    fun `Should track when a preset level is finished in a single level game`() {
        // GIVEN
        val level = PresetLevel(presetLevelKey, exercise, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        val repository = createRepository(presetLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(singleLevelGameTracking).trackLevelFinished(level)
    }

    @Test
    fun `Should track when a generated level is finished in a single level game`() {
        // GIVEN
        val level = GeneratedLevel(generatedLevelKey, exercise, false, 0, 0)

        val steps: GameSteps = mock { on { validStepsCount } doReturn 1 }

        val repository = createRepository(generatedLevelKey)

        // WHEN
        repository.finishLevel(level, steps)

        // THEN
        verify(singleLevelGameTracking).trackLevelFinished(level)
    }

    private fun createRepository(singleLevelKey: LevelKey) =
            SingleLevelGameLevelRepository(
                    singleLevelKey,
                    eventLevelDatabase,
                    presetLevelDatabase,
                    generatedLevelDatabase,
                    singleLevelGameTracking)

    private fun setupEventLevelLoading(level: EventLevel) =
        whenever(eventLevelDatabase.loadLevel(level.levelKey)) doReturn level

    private fun setupPresetLevelLoading(level: PresetLevel) =
        whenever(presetLevelDatabase.loadLevel(level.levelKey)) doReturn level

    private fun setupGeneratedLevelLoading(level: GeneratedLevel) =
        whenever(generatedLevelDatabase.loadLevel(level.levelKey)) doReturn level
}