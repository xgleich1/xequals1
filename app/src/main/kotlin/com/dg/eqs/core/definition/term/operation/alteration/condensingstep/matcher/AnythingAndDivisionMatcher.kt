package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object AnythingAndDivisionMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfLastDivision = lastIndexOf<Division>()

        indexOfLastDivision != -1 && indexOfLastDivision != 0
    }

    override fun matches(left: Terms, right: Terms) = right.isMonad<Division>()
}