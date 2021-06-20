package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object DashOperationAndDashOperationExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val dashOperations = filterIsInstance<DashOperation>()

        val left = termsOf(dashOperations.first)
        val right = termsOf(dashOperations.second)

        left and right
    }
}