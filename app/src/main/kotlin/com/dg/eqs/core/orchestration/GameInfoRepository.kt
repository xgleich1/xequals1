package com.dg.eqs.core.orchestration

import com.dg.eqs.base.abbreviation.AnyLevel
import com.dg.eqs.core.information.valid.ValidInfo


interface GameInfoRepository {
    fun loadInitialInfo(level: AnyLevel): ValidInfo
}