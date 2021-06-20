package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionAndAnythingExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfFirstDivision = indexOf<Division>()

        val left = termsOf(get(indexOfFirstDivision))
        val right = termsOf(get(indexOfFirstDivision + 1))

        left and right
    }
}