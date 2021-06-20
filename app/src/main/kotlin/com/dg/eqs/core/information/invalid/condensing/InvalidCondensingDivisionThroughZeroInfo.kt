package com.dg.eqs.core.information.invalid.condensing

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.invalid.InvalidInfo


object InvalidCondensingDivisionThroughZeroInfo : InvalidInfo() {
    override val content = LayoutRes(
            R.layout.info_division_through_zero)

    override val isVital = false
}