package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object ZeroAndAnythingMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfFirstZero = indexOf(Term::isZero)

        indexOfFirstZero != -1 && indexOfFirstZero != lastIndex
    }

    override fun matches(left: Terms, right: Terms) = left.allZeros()
}