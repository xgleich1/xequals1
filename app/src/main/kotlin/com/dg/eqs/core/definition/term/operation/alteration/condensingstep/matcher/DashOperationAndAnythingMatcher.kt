package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object DashOperationAndAnythingMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfFirstDashOperation = indexOf<DashOperation>()

        indexOfFirstDashOperation != -1 && indexOfFirstDashOperation != lastIndex
    }

    override fun matches(left: Terms, right: Terms) = left.isMonad<DashOperation>()
}