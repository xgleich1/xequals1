package com.dg.eqs.core.information.valid

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes


object ReduceInfo : ValidInfo() {
    override val content = LayoutRes(
            R.layout.info_reduce)

    override val isVital = false
}