package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.tracking.Tracking
import com.dg.eqs.core.progression.Level.*


class SingleLevelGameTracking(private val tracking: Tracking) {
    fun trackLevelLoaded(level: AnyLevel) = when(level) {
        is EventLevel -> trackEventLevelLoaded(level)
        is PresetLevel -> trackPresetLevelLoaded(level)
        is GeneratedLevel -> trackGeneratedLevelLoaded(level)
    }

    fun trackLevelFinished(level: AnyLevel) = when(level) {
        is EventLevel -> trackEventLevelFinished(level)
        is PresetLevel -> trackPresetLevelFinished(level)
        is GeneratedLevel -> trackGeneratedLevelFinished(level)
    }

    private fun trackEventLevelLoaded(level: EventLevel) =
            tracking.trackEvent("single_event_level_loaded_${level.levelKey.rawKey}")

    private fun trackPresetLevelLoaded(level: PresetLevel) =
            tracking.trackEvent("single_preset_level_loaded_${level.levelKey.rawKey}")

    private fun trackGeneratedLevelLoaded(level: GeneratedLevel) =
            tracking.trackEvent("single_generated_level_loaded_${level.levelKey.rawKey}")

    private fun trackEventLevelFinished(level: EventLevel) =
            tracking.trackEvent("single_event_level_finished_${level.levelKey.rawKey}")

    private fun trackPresetLevelFinished(level: PresetLevel) =
            tracking.trackEvent("single_preset_level_finished_${level.levelKey.rawKey}")

    private fun trackGeneratedLevelFinished(level: GeneratedLevel) =
            tracking.trackEvent("single_generated_level_finished_${level.levelKey.rawKey}")
}