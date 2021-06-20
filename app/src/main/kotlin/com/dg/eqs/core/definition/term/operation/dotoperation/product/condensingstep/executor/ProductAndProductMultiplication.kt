package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object ProductAndProductMultiplication : CondensingExecutor<Product, Product> {
    override fun execute(left: Product, right: Product): Terms {
        val unsignedResult = multiplyUnsigned(left, right)

        return termsOf(unsignedResult.toSignedResult(left, right))
    }

    private fun multiplyUnsigned(left: Product, right: Product) =
            PositiveProduct(left.operands + right.operands)

    private fun Term.toSignedResult(left: Product, right: Product) =
            if(left.isPositive == right.isPositive) this else invert()
}