package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object ProductAndProductMatcher : CondensingMatcher {
    override fun matches(operands: Terms) =
            operands.filterIsInstance<Product>().size >= 2

    override fun matches(left: Terms, right: Terms) =
            left.isMonad<Product>() && right.isMonad<Product>()
}