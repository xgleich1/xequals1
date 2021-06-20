package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object ProductAndAnythingMultiplication : CondensingExecutor<Product, Terms> {
    override fun execute(left: Product, right: Terms) = termsOf(left.multiplyToBack(right))
}