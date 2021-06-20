package com.dg.eqs.core.orchestration.endlesslevelgame.info

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.valid.ValidInfo


class EndlessLevelGameAppReviewInfo : ValidInfo() {
    override val content = LayoutRes(
            R.layout.info_app_review)

    override val isVital = true
}