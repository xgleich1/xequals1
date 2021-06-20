package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.progression.Level.*
import com.dg.eqs.core.progression.LevelKey.*
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
class SingleLevelGameTrackingTest {
    @Mock
    private lateinit var tracking: Tracking
    @InjectMocks
    private lateinit var singleLevelGameTracking: SingleLevelGameTracking

    @Mock
    private lateinit var eventLevel: EventLevel
    @Mock
    private lateinit var presetLevel: PresetLevel
    @Mock
    private lateinit var generatedLevel: GeneratedLevel


    @Before
    fun setUp() {
        whenever(eventLevel.levelKey) doReturn
                EventLevelKey(TEST_EVENT_LEVEL_RAW_KEY)

        whenever(presetLevel.levelKey) doReturn
                PresetLevelKey(TEST_PRESET_LEVEL_RAW_KEY)

        whenever(generatedLevel.levelKey) doReturn
                GeneratedLevelKey(TEST_GENERATED_LEVEL_RAW_KEY)
    }

    @Test
    fun `Should track when a event level is loaded for a single level game`() {
        // WHEN
        singleLevelGameTracking.trackLevelLoaded(eventLevel)

        // THEN
        verify(tracking).trackEvent(
                "single_event_level_loaded_$TEST_EVENT_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a preset level is loaded for a single level game`() {
        // WHEN
        singleLevelGameTracking.trackLevelLoaded(presetLevel)

        // THEN
        verify(tracking).trackEvent(
                "single_preset_level_loaded_$TEST_PRESET_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a generated level is loaded for a single level game`() {
        // WHEN
        singleLevelGameTracking.trackLevelLoaded(generatedLevel)

        // THEN
        verify(tracking).trackEvent(
                "single_generated_level_loaded_$TEST_GENERATED_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a event level is finished in a single level game`() {
        // WHEN
        singleLevelGameTracking.trackLevelFinished(eventLevel)

        // THEN
        verify(tracking).trackEvent(
                "single_event_level_finished_$TEST_EVENT_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a preset level is finished in a single level game`() {
        // WHEN
        singleLevelGameTracking.trackLevelFinished(presetLevel)

        // THEN
        verify(tracking).trackEvent(
                "single_preset_level_finished_$TEST_PRESET_LEVEL_RAW_KEY")
    }

    @Test
    fun `Should track when a generated level is finished in a single level game`() {
        // WHEN
        singleLevelGameTracking.trackLevelFinished(generatedLevel)

        // THEN
        verify(tracking).trackEvent(
                "single_generated_level_finished_$TEST_GENERATED_LEVEL_RAW_KEY")
    }

    private companion object {
        private const val TEST_EVENT_LEVEL_RAW_KEY = 1L
        private const val TEST_PRESET_LEVEL_RAW_KEY = 2L
        private const val TEST_GENERATED_LEVEL_RAW_KEY = 3L
    }
}