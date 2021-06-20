package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object ProductAndAnythingMatcher : CondensingMatcher {
    override fun matches(operands: Terms) = with(operands) {
        val indexOfFirstProduct = indexOf<Product>()

        indexOfFirstProduct != -1 && indexOfFirstProduct != lastIndex
    }

    override fun matches(left: Terms, right: Terms) = left.isMonad<Product>()
}