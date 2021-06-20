package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher

import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isDivisionThroughPositiveOne
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object AnythingAndDivisionThroughPositiveOneMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfLastDivisionThroughPositiveOne =
                lastIndexOf(Term::isDivisionThroughPositiveOne)

        indexOfLastDivisionThroughPositiveOne != -1
                && indexOfLastDivisionThroughPositiveOne != 0
    }

    override fun matches(left: Terms, right: Terms) =
            right.isSingle && right.single.isDivisionThroughPositiveOne
}