package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object ValueAndValueExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val values = filterIsInstance<Value>()

        val left = termsOf(values.first)
        val right = termsOf(values.second)

        left and right
    }
}