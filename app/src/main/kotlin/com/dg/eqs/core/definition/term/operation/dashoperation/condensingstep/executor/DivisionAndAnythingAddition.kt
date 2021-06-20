package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object DivisionAndAnythingAddition : CondensingExecutor<Division, Terms> {
    override fun execute(left: Division, right: Terms): Terms {
        val (exemptedNumerator, exemptedDenominator) =
                left.applySign()

        val expandedAnything = right
                .expandWith(exemptedDenominator.copy())

        return termsOf(PositiveDivision(
                PositiveDashOperation(
                        exemptedNumerator,
                        expandedAnything),
                exemptedDenominator))
    }

    private fun Terms.expandWith(denominator: Term) = if(first.isPositive) {
        PositiveProduct(denominator, toSingleOrDashOperation())
    } else {
        NegativeProduct(denominator, mapInvert().toSingleOrDashOperation())
    }

    private fun Terms.toSingleOrDashOperation() = singleOrNull() ?: PositiveDashOperation(this)
}