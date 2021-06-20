package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object AnythingAndZeroExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfLastZero = lastIndexOf(Term::isZero)

        val left = termsOf(get(indexOfLastZero - 1))
        val right = termsOf(get(indexOfLastZero))

        left and right
    }
}