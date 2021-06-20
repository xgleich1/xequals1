package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object ProductAndAnythingExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfFirstProduct = indexOf<Product>()

        val left = termsOf(get(indexOfFirstProduct))
        val right = termsOf(get(indexOfFirstProduct + 1))

        left and right
    }
}