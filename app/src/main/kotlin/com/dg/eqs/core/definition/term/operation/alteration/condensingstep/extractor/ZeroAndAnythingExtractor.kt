package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object ZeroAndAnythingExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfFirstZero = indexOf(Term::isZero)

        val left = termsOf(get(indexOfFirstZero))
        val right = termsOf(get(indexOfFirstZero + 1))

        left and right
    }
}