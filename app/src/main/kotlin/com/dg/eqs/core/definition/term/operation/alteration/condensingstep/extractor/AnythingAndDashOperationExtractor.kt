package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object AnythingAndDashOperationExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfLastDashOperation = lastIndexOf<DashOperation>()

        val left = termsOf(get(indexOfLastDashOperation - 1))
        val right = termsOf(get(indexOfLastDashOperation))

        left and right
    }
}