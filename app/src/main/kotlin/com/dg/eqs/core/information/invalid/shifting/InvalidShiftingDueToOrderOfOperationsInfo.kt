package com.dg.eqs.core.information.invalid.shifting

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.invalid.InvalidInfo


object InvalidShiftingDueToOrderOfOperationsInfo : InvalidInfo() {
    override val content = LayoutRes(
            R.layout.info_order_of_operations)

    override val isVital = false
}