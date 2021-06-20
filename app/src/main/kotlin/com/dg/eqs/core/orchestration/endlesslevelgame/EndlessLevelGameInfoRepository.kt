package com.dg.eqs.core.orchestration.endlesslevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.base.persistence.offline.OfflinePersistence
import com.dg.eqs.core.information.valid.ValidInfo
import com.dg.eqs.core.orchestration.GameInfoRepository
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameAppReviewInfo
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameAppReviewInfoLoadedKey
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameHowToInfo
import com.dg.eqs.core.orchestration.endlesslevelgame.info.EndlessLevelGameHowToInfoMadeVitalKey
import com.dg.eqs.core.progression.Level.GeneratedLevel


class EndlessLevelGameInfoRepository(
        private val offlinePersistence: OfflinePersistence
) : GameInfoRepository {

    override fun loadInitialInfo(level: AnyLevel) = when {
        shouldLoadAppReviewInfo(level) -> loadAppReviewInfo()

        else -> loadHowToInfo()
    }

    private fun shouldLoadAppReviewInfo(level: AnyLevel) =
            level is GeneratedLevel
                    && !loadAppReviewInfoLoaded()

    private fun loadAppReviewInfo(): ValidInfo {
        saveAppReviewInfoLoaded()

        return EndlessLevelGameAppReviewInfo()
    }

    private fun loadHowToInfo(): ValidInfo {
        val howToInfoMadeVital =
                loadHowToInfoMadeVital()

        if(!howToInfoMadeVital) {
            saveHowToInfoMadeVital()
        }

        return EndlessLevelGameHowToInfo(
                isVital = !howToInfoMadeVital)
    }

    private fun loadAppReviewInfoLoaded() = offlinePersistence
            .loadBoolean(EndlessLevelGameAppReviewInfoLoadedKey, false)

    private fun saveAppReviewInfoLoaded() = offlinePersistence
            .saveBoolean(EndlessLevelGameAppReviewInfoLoadedKey, true)

    private fun loadHowToInfoMadeVital() = offlinePersistence
            .loadBoolean(EndlessLevelGameHowToInfoMadeVitalKey, false)

    private fun saveHowToInfoMadeVital() = offlinePersistence
            .saveBoolean(EndlessLevelGameHowToInfoMadeVitalKey, true)
}