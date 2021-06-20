package com.dg.eqs.core.information.invalid.condensing

import com.dg.eqs.R
import com.dg.eqs.base.enveloping.LayoutRes
import com.dg.eqs.core.information.invalid.InvalidInfo


object InvalidCondensingDueToOrderOfOperationsInfo : InvalidInfo() {
    override val content = LayoutRes(
            R.layout.info_order_of_operations)

    override val isVital = false
}