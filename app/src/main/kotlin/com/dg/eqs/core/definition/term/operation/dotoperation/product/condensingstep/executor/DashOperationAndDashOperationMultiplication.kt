package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object DashOperationAndDashOperationMultiplication
    : CondensingExecutor<DashOperation, DashOperation> {

    override fun execute(left: DashOperation, right: DashOperation): Terms {
        val expandedRightOperands = right.operands.map {
            val copiedLeft = left.copy()

            if(it is Product) {
                it.multiplyToFront(termsOf(copiedLeft))
            } else {
                PositiveProduct(termsOf(copiedLeft, it))
            }
        }

        return termsOf(right.recreate(expandedRightOperands))
    }
}