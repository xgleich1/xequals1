package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object AnythingAndDivisionAddition : CondensingExecutor<Terms, Division> {
    override fun execute(left: Terms, right: Division): Terms {
        val (exemptedNumerator, exemptedDenominator) =
                right.applySign()

        val expandedAnything = left
                .expandWith(exemptedDenominator.copy())

        return termsOf(PositiveDivision(
                PositiveDashOperation(
                        expandedAnything,
                        exemptedNumerator),
                exemptedDenominator))
    }

    private fun Terms.expandWith(denominator: Term) =
            PositiveProduct(toSingleOrDashOperation(), denominator)

    private fun Terms.toSingleOrDashOperation() =
            singleOrNull() ?: PositiveDashOperation(this)
}