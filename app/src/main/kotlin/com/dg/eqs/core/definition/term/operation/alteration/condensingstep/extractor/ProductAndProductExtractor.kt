package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object ProductAndProductExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val products = filterIsInstance<Product>()

        val left = termsOf(products.first)
        val right = termsOf(products.second)

        left and right
    }
}