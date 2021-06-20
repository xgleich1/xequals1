package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object DashOperationAndAnythingMultiplication : CondensingExecutor<DashOperation, Terms> {
    override fun execute(left: DashOperation, right: Terms): Terms {
        val expandedDashOperationOperands = left.operands.map {
            val copiedAnything = right.mapCopy()

            if(it is Product) {
                it.multiplyToBack(copiedAnything)
            } else {
                PositiveProduct(termsOf(it, copiedAnything))
            }
        }

        return termsOf(left.recreate(expandedDashOperationOperands))
    }
}