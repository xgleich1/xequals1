package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isOne
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object OneAndAnythingExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfFirstOne = indexOf(Term::isOne)

        val left = termsOf(get(indexOfFirstOne))
        val right = termsOf(get(indexOfFirstOne + 1))

        left and right
    }
}