package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isOne
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object OneAndAnythingMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfFirstOne = indexOf(Term::isOne)

        indexOfFirstOne != -1 && indexOfFirstOne != lastIndex
    }

    override fun matches(left: Terms, right: Terms) = left.allOnes()
}