package com.dg.eqs.core.generation.generatedlevel.equalsrelation

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.Parent
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException


class EqualsRelationValidator {
    fun validate(equalsRelation: EqualsRelation) = try {
        val (left, right) = equalsRelation

        val resolvedLeft = left.resolve()
        val resolvedRight = right.resolve()

        !isSideEliminated(resolvedLeft, resolvedRight) &&
        !isVariableEliminated(resolvedLeft, resolvedRight) &&
        !isVariableInDenominator(equalsRelation)
    } catch(e: DivisionThroughZeroException) {
        false
    }

    private fun isSideEliminated(resolvedLeft: Term, resolvedRight: Term) =
            resolvedLeft.isZero || resolvedRight.isZero

    private fun isVariableEliminated(resolvedLeft: Term, resolvedRight: Term) =
            resolvedLeft.isConstant() && resolvedRight.isConstant()

    private fun isVariableInDenominator(origin: AnyOrigin): Boolean =
            isDivisionWithVariableInDenominator(origin) ||
            isParentOfDivisionWithVariableInDenominator(origin)

    private fun isDivisionWithVariableInDenominator(origin: AnyOrigin) =
            origin is Division && !origin.denominator.isConstant()

    private fun isParentOfDivisionWithVariableInDenominator(origin: AnyOrigin) =
            origin is Parent && origin.any { isVariableInDenominator(it) }
}