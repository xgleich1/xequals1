package com.dg.eqs.core.information.invalid.condensing

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.invalid.InvalidInfo


object InvalidCondensingSubtractionWithVariableInfo : InvalidInfo() {
    override val content = LayoutRes(
            R.layout.info_condensing_with_variable)

    override val isVital = false
}