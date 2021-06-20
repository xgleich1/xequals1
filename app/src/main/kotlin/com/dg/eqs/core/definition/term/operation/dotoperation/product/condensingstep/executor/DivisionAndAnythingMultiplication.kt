package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object DivisionAndAnythingMultiplication : CondensingExecutor<Division, Terms> {
    override fun execute(left: Division, right: Terms) = with(left) {
        val multipliedNumerator = if(numerator is Product) {
            numerator.multiplyToBack(right)
        } else {
            PositiveProduct(termsOf(numerator, right))
        }

        termsOf(recreate(multipliedNumerator, denominator))
    }
}