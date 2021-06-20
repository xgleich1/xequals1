package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.findDistinctPair
import com.dg.eqs.base.extension.left
import com.dg.eqs.base.extension.right
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionAndEqualDenominatorDivisionExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val firstDivisionsWithEqualDenominator =
                 filterIsInstance<Division>()
                .findDistinctPair {
                    left.denominator == right.denominator
                }

        val left = termsOf(firstDivisionsWithEqualDenominator?.left)
        val right = termsOf(firstDivisionsWithEqualDenominator?.right)

        left and right
    }
}