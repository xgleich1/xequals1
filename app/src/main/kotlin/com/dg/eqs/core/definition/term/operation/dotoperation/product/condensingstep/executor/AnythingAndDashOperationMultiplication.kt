package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object AnythingAndDashOperationMultiplication : CondensingExecutor<Terms, DashOperation> {
    override fun execute(left: Terms, right: DashOperation): Terms {
        val expandedDashOperationOperands = right.operands.map {
            val copiedAnything = left.mapCopy()

            if(it is Product) {
                it.multiplyToFront(copiedAnything)
            } else {
                PositiveProduct(termsOf(copiedAnything, it))
            }
        }

        return termsOf(right.recreate(expandedDashOperationOperands))
    }
}