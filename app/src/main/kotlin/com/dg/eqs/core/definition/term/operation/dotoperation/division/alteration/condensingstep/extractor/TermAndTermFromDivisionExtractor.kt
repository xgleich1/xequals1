package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object TermAndTermFromDivisionExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val left = termsOf(first)
        val right = termsOf(second)

        left and right
    }
}