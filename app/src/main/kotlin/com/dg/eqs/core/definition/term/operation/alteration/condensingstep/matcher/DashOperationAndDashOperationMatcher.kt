package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object DashOperationAndDashOperationMatcher : CondensingMatcher {
    override fun matches(operands: Terms) =
            operands.filterIsInstance<DashOperation>().size >= 2

    override fun matches(left: Terms, right: Terms) =
            left.isMonad<DashOperation>() && right.isMonad<DashOperation>()
}