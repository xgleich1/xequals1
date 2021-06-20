package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object DivisionAndDivisionMultiplication : CondensingExecutor<Division, Division> {
    override fun execute(left: Division, right: Division): Terms {
        val unsignedResult = multiplyUnsigned(left, right)

        return termsOf(unsignedResult.toSignedResult(left, right))
    }

    private fun multiplyUnsigned(left: Division, right: Division) =
            PositiveDivision(
                    PositiveProduct(
                            left.numerator,
                            right.numerator),
                    PositiveProduct(
                            left.denominator,
                            right.denominator))

    private fun Term.toSignedResult(left: Division, right: Division) =
            if(left.isPositive == right.isPositive) this else invert()
}