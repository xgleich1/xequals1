package com.dg.eqs.core.definition.term.operation.dotoperation.division

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.alteration.DefaultOperandsPackaging
import com.dg.eqs.core.definition.term.operation.alteration.DefaultOperandsReplacing
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import com.dg.eqs.core.definition.term.operation.dotoperation.DotOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionOperandsCondensing
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionOperandsRemoving
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.*


abstract class Division(val numerator: Term, val denominator: Term)
    : DotOperation(termsOf(numerator, denominator)) {

    override val alteration = OperationAlteration(
            DivisionOperandsRemoving(),
            DefaultOperandsReplacing(),
            DivisionOperandsCondensing(
                    ValueAndValueWithGcdGreaterOneDivisionStep,
                    ZeroAndAnythingDivisionStep,
                    AnythingAndOneDivisionStep,
                    TermAndEqualTermDivisionStep,
                    TermAndOppositeTermDivisionStep,
                    DivisionAndDivisionDivisionStep,
                    DivisionAndAnythingDivisionStep,
                    AnythingAndDivisionDivisionStep,
                    NegativeTermAndNegativeTermDivisionStep,
                    PositiveTermAndNegativeTermDivisionStep,
                    NegativeTermAndPositiveTermDivisionStep),
            DefaultOperandsPackaging())

    val isDivisionThroughPositiveOne = denominator == PositiveValue(1)


    override fun toString() = "/${super.toString()}"

    override fun recreate(newOperands: Terms): Division {
        require(newOperands.size == 2)

        val (numerator, denominator) = newOperands

        return recreate(numerator, denominator)
    }

    abstract fun recreate(newNumerator: Term, newDenominator: Term): Division
}