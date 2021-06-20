package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object AnythingAndDivisionDivision : CondensingExecutor<Term, Division> {
    override fun execute(left: Term, right: Division): Terms {
        val unsignedResult = divideUnsigned(left, right)

        return termsOf(unsignedResult.toSignedResult(left, right))
    }

    private fun divideUnsigned(numerator: Term, denominator: Division) =
            PositiveDivision(
                    PositiveProduct(
                            numerator.toPositiveTerm(),
                            denominator.denominator),
                    denominator.numerator)

    private fun Term.toPositiveTerm() = if(isPositive) this else invert()

    private fun Term.toSignedResult(numerator: Term, denominator: Division) =
            if(numerator.isPositive == denominator.isPositive) this else invert()
}