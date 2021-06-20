package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object AnythingAndProductMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfLastProduct = lastIndexOf<Product>()

        indexOfLastProduct != -1 && indexOfLastProduct != 0
    }

    override fun matches(left: Terms, right: Terms) = right.isMonad<Product>()
}