package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isOne
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object AnythingAndOneMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfLastOne = lastIndexOf(Term::isOne)

        indexOfLastOne != -1 && indexOfLastOne != 0
    }

    override fun matches(left: Terms, right: Terms) = right.allOnes()
}