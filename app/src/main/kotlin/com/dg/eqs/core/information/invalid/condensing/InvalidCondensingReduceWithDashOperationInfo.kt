package com.dg.eqs.core.information.invalid.condensing

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.invalid.InvalidInfo


object InvalidCondensingReduceWithDashOperationInfo : InvalidInfo() {
    override val content = LayoutRes(
            R.layout.info_reduce_with_dash_operation)

    override val isVital = false
}