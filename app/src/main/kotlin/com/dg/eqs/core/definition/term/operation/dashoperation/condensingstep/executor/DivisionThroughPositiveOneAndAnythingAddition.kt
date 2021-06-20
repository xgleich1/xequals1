package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision


object DivisionThroughPositiveOneAndAnythingAddition : CondensingExecutor<Division, Terms> {
    override fun execute(left: Division, right: Terms): Terms {
        val (exemptedNumerator, exemptedDenominator) =
                left.applySign()

        return termsOf(PositiveDivision(
                PositiveDashOperation(termsOf(
                        exemptedNumerator,
                        right)),
                exemptedDenominator))
    }
}