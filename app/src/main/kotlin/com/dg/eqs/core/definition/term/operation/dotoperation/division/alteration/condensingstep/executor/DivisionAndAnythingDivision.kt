package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object DivisionAndAnythingDivision : CondensingExecutor<Division, Term> {
    override fun execute(left: Division, right: Term): Terms {
        val unsignedResult = divideUnsigned(left, right)

        return termsOf(unsignedResult.toSignedResult(left, right))
    }

    private fun divideUnsigned(numerator: Division, denominator: Term) =
            PositiveDivision(
                    numerator.numerator,
                    PositiveProduct(
                            numerator.denominator,
                            denominator.toPositiveTerm()))

    private fun Term.toPositiveTerm() = if(isPositive) this else invert()

    private fun Term.toSignedResult(numerator: Division, denominator: Term) =
            if(numerator.isPositive == denominator.isPositive) this else invert()
}