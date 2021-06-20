package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision


object AnythingAndDivisionThroughPositiveOneAddition : CondensingExecutor<Terms, Division> {
    override fun execute(left: Terms, right: Division): Terms {
        val (exemptedNumerator, exemptedDenominator) =
                right.applySign()

        return termsOf(PositiveDivision(
                PositiveDashOperation(termsOf(
                        left,
                        exemptedNumerator)),
                exemptedDenominator))
    }
}