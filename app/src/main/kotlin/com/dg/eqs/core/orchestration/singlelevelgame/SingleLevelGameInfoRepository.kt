package com.dg.eqs.core.orchestration.singlelevelgame

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.core.orchestration.GameInfoRepository
import com.dg.eqs.core.orchestration.singlelevelgame.info.SingleLevelGameHowToInfo


class SingleLevelGameInfoRepository : GameInfoRepository {
    override fun loadInitialInfo(level: AnyLevel) = SingleLevelGameHowToInfo
}