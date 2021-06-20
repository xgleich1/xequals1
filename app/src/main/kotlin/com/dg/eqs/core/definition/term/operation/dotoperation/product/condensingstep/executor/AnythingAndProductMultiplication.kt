package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object AnythingAndProductMultiplication : CondensingExecutor<Terms, Product> {
    override fun execute(left: Terms, right: Product) = termsOf(right.multiplyToFront(left))
}