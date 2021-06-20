package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object DivisionAndDivisionAddition : CondensingExecutor<Division, Division> {
    override fun execute(left: Division, right: Division): Terms {
        val (exemptedLeftNumerator, exemptedLeftDenominator) = left.applySign()
        val (originalRightNumerator, originalRightDenominator) = right

        val expandedLeftNumerator = PositiveProduct(
                exemptedLeftNumerator,
                originalRightDenominator.copy())

        val expandedRightNumerator = PositiveProduct(
                exemptedLeftDenominator.copy(),
                originalRightNumerator)

        val signedExpandedRightNumerator =
                if(right.isPositive) {
                    expandedRightNumerator
                } else {
                    expandedRightNumerator.invert()
                }

        return termsOf(PositiveDivision(
                PositiveDashOperation(
                        expandedLeftNumerator,
                        signedExpandedRightNumerator),
                PositiveProduct(
                        exemptedLeftDenominator,
                        originalRightDenominator)))
    }
}