package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object AnythingAndDivisionMultiplication : CondensingExecutor<Terms, Division> {
    override fun execute(left: Terms, right: Division) = with(right) {
        val multipliedNumerator = if(numerator is Product) {
            numerator.multiplyToFront(left)
        } else {
            PositiveProduct(termsOf(left, numerator))
        }

        termsOf(recreate(multipliedNumerator, denominator))
    }
}