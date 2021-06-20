package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object AnythingAndZeroMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfLastZero = lastIndexOf(Term::isZero)

        indexOfLastZero != -1 && indexOfLastZero != 0
    }

    override fun matches(left: Terms, right: Terms) = right.allZeros()
}