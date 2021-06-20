package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher

import com.dg.eqs.base.extension.*
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionAndEqualDenominatorDivisionMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = operands
            .filterIsInstance<Division>()
            .containsDistinctPair {
                haveEqualDenominators(left, right)
            }

    override fun matches(left: Terms, right: Terms): Boolean {
        if(!left.isMonad<Division>()) return false
        if(!right.isMonad<Division>()) return false

        val leftDivision = left.typedSingle<Division>()
        val rightDivision = right.typedSingle<Division>()

        return haveEqualDenominators(leftDivision, rightDivision)
    }

    private fun haveEqualDenominators(left: Division, right: Division) =
            left.denominator == right.denominator
}