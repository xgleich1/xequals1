package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isDivisionThroughPositiveOne
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object DivisionThroughPositiveOneAndAnythingExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfFirstDivisionThroughPositiveOne =
                indexOf(Term::isDivisionThroughPositiveOne)

        val left = termsOf(get(indexOfFirstDivisionThroughPositiveOne))
        val right = termsOf(get(indexOfFirstDivisionThroughPositiveOne + 1))

        left and right
    }
}