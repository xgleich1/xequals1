package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object AnythingAndDashOperationMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfLastDashOperation = lastIndexOf<DashOperation>()

        indexOfLastDashOperation != -1 && indexOfLastDashOperation != 0
    }

    override fun matches(left: Terms, right: Terms) = right.isMonad<DashOperation>()
}