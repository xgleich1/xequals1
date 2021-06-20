package com.dg.eqs.core.orchestration.singlelevelgame.info

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.valid.ValidInfo


object SingleLevelGameHowToInfo : ValidInfo() {
    override val content = LayoutRes(
            R.layout.info_how_to)

    override val isVital = false
}