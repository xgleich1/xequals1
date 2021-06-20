package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isDivisionThroughPositiveOne
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object AnythingAndDivisionThroughPositiveOneExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfLastDivisionThroughPositiveOne =
                lastIndexOf(Term::isDivisionThroughPositiveOne)

        val left = termsOf(get(indexOfLastDivisionThroughPositiveOne - 1))
        val right = termsOf(get(indexOfLastDivisionThroughPositiveOne))

        left and right
    }
}