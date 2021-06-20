package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object PositiveTermAndNegativeTermInDivisionMatcher : CondensingMatcher {
    override fun matches(operands: Terms) =
            operands.first.isPositive && operands.second.isNegative

    override fun matches(left: Terms, right: Terms) = matches(left + right)
}