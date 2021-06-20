package com.dg.eqs.core.information.valid

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes


object MultiplicationInfo : ValidInfo() {
    override val content = LayoutRes(
            R.layout.info_multiplication)

    override val isVital = false
}