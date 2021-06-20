package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision


object DivisionAndEqualDenominatorDivisionAddition : CondensingExecutor<Division, Division> {
    override fun execute(left: Division, right: Division): Terms {
        val (exemptedLeftNumerator, exemptedLeftDenominator) = left.applySign()
        val (exemptedRightNumerator, _) = right.applySign()

        return termsOf(PositiveDivision(
                PositiveDashOperation(
                        exemptedLeftNumerator,
                        exemptedRightNumerator),
                exemptedLeftDenominator))
    }
}