package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.progression.Level.*
import com.dg.eqs.core.progression.LevelKey
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class EndlessLevelGameTrackingTest {
    @Mock
    private lateinit var tracking: Tracking
    @InjectMocks
    private lateinit var endlessLevelGameTracking: EndlessLevelGameTracking

    @Mock
    private lateinit var eventLevel: EventLevel
    @Mock
    private lateinit var presetLevel: PresetLevel
    @Mock
    private lateinit var generatedLevel: GeneratedLevel


    @Before
    fun setUp() {
        whenever(eventLevel.levelKey) doReturn
                LevelKey.EventLevelKey(TEST_EVENT_LEVEL_RAW_KEY)

        whenever(presetLevel.levelKey) doReturn
                LevelKey.PresetLevelKey(TEST_PRESET_LEVEL_RAW_KEY)

        whenever(generatedLevel.levelKey) doReturn
                LevelKey.GeneratedLevelKey(TEST_GENERATED_LEVEL_RAW_KEY)
    }

    @Test
    fun `Should track when a event level is loaded for a endless level game`() {
        // WHEN
        endlessLevelGameTracking.trackLevelLoaded(eventLevel)

        // THEN
        verify(tracking).trackEvent(
                "endless_event_level_loaded_$TEST_EVENT_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a preset level is loaded for a endless level game`() {
        // WHEN
        endlessLevelGameTracking.trackLevelLoaded(presetLevel)

        // THEN
        verify(tracking).trackEvent(
                "endless_preset_level_loaded_$TEST_PRESET_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a generated level is loaded for a endless level game`() {
        // WHEN
        endlessLevelGameTracking.trackLevelLoaded(generatedLevel)

        // THEN
        verify(tracking).trackEvent(
                "endless_generated_level_loaded_$TEST_GENERATED_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a event level is finished in a endless level game`() {
        // WHEN
        endlessLevelGameTracking.trackLevelFinished(eventLevel)

        // THEN
        verify(tracking).trackEvent(
                "endless_event_level_finished_$TEST_EVENT_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a preset level is finished in a endless level game`() {
        // WHEN
        endlessLevelGameTracking.trackLevelFinished(presetLevel)

        // THEN
        verify(tracking).trackEvent(
                "endless_preset_level_finished_$TEST_PRESET_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a generated level is finished in a endless level game`() {
        // WHEN
        endlessLevelGameTracking.trackLevelFinished(generatedLevel)

        // THEN
        verify(tracking).trackEvent(
                "endless_generated_level_finished_$TEST_GENERATED_LEVEL_RAW_KEY")
    }

    private companion object {
        private const val TEST_EVENT_LEVEL_RAW_KEY = 1L
        private const val TEST_PRESET_LEVEL_RAW_KEY = 2L
        private const val TEST_GENERATED_LEVEL_RAW_KEY = 3L
    }
}