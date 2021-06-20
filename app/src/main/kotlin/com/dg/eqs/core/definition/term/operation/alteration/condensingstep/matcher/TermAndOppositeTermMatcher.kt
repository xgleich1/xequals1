package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.*
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object TermAndOppositeTermMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        containsDistinctPair { areOpposing(left, right) }
    }

    override fun matches(left: Terms, right: Terms): Boolean {
        if(left.isNotSingle) return false
        if(right.isNotSingle) return false

        return areOpposing(left.single, right.single)
    }

    private fun areOpposing(left: Term, right: Term) =
            left == right.invert()
}