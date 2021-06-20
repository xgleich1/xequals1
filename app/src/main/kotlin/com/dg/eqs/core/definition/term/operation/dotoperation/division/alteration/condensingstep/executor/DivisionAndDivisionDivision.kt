package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object DivisionAndDivisionDivision : CondensingExecutor<Division, Division> {
    override fun execute(left: Division, right: Division): Terms {
        val unsignedResult = divideUnsigned(left, right)

        return termsOf(unsignedResult.toSignedResult(left, right))
    }

    private fun divideUnsigned(numerator: Division, denominator: Division) =
            PositiveDivision(
                    PositiveProduct(
                            numerator.numerator,
                            denominator.denominator),
                    PositiveProduct(
                            numerator.denominator,
                            denominator.numerator))

    private fun Term.toSignedResult(numerator: Division, denominator: Division) =
            if(numerator.isPositive == denominator.isPositive) this else invert()
}