package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object AnythingAndProductExtractor : CondensingExtractor {
    override fun extract(operands: Terms) = with(operands) {
        val indexOfLastProduct = lastIndexOf<Product>()

        val left = termsOf(get(indexOfLastProduct - 1))
        val right = termsOf(get(indexOfLastProduct))

        left and right
    }
}