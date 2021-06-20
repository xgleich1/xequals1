package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision

import com.dg.eqs.core.definition.term.isOne
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionEvent.*
import com.google.common.math.IntMath.gcd


class SingleSelectionCondensingInDivisionDetector {
    private val Link.numerator get() = (mutualParent as Division).numerator
    private val Link.denominator get() = (mutualParent as Division).denominator


    fun detect(link: Link) = with(link) {
        when {
            isInvolvingDenominatorOfZero() -> InvalidSingleSelectionCondensingDivisionThroughZero

            isInvolvingPositiveValueAndPositiveValueWithGcdOfOne() ||
            isInvolvingNotPositiveZeroAndPositiveVariable() ||
            isInvolvingNotPositiveZeroAndPositiveDashOperation() ||
            isInvolvingNotPositiveZeroAndPositiveProduct() ||
            isInvolvingPositiveVariableAndNotPositiveOne() ||
            isInvolvingPositiveVariableAndDifferentPositiveVariable() ||
            isInvolvingPositiveVariableAndPositiveDashOperation() ||
            isInvolvingPositiveVariableAndPositiveProduct() ||
            isInvolvingPositiveDashOperationAndNotPositiveOne() ||
            isInvolvingPositiveDashOperationAndPositiveVariable() ||
            isInvolvingPositiveDashOperationAndDifferentPositiveDashOperation() ||
            isInvolvingPositiveDashOperationAndPositiveProduct() ||
            isInvolvingPositiveProductAndNotPositiveOne() ||
            isInvolvingPositiveProductAndPositiveVariable() ||
            isInvolvingPositiveProductAndPositiveDashOperation() ||
            isInvolvingPositiveProductAndDifferentPositiveProduct() -> InvalidSingleSelectionCondensingDivision

            else -> ValidSingleSelectionCondensingDivision
        }
    }

    private fun Link.isInvolvingDenominatorOfZero() = denominator.isZero

    private fun Link.isInvolvingPositiveValueAndPositiveValueWithGcdOfOne(): Boolean {
        if(denominator.isOne) return false

        val numeratorValue = numerator as? PositiveValue ?: return false
        val denominatorValue = denominator as? PositiveValue ?: return false

        return gcd(numeratorValue.unsignedNumber, denominatorValue.unsignedNumber) == 1
    }

    private fun Link.isInvolvingNotPositiveZeroAndPositiveVariable(): Boolean {
        if(numerator !is PositiveValue) return false
        if(denominator !is PositiveVariable) return false

        return numerator != PositiveValue(0)
    }

    private fun Link.isInvolvingNotPositiveZeroAndPositiveDashOperation(): Boolean {
        if(numerator !is PositiveValue) return false
        if(denominator !is PositiveDashOperation) return false

        return numerator != PositiveValue(0)
    }

    private fun Link.isInvolvingNotPositiveZeroAndPositiveProduct(): Boolean {
        if(numerator !is PositiveValue) return false
        if(denominator !is PositiveProduct) return false

        return numerator != PositiveValue(0)
    }

    private fun Link.isInvolvingPositiveVariableAndNotPositiveOne(): Boolean {
        if(numerator !is PositiveVariable) return false
        if(denominator !is PositiveValue) return false

        return denominator != PositiveValue(1)
    }

    private fun Link.isInvolvingPositiveVariableAndDifferentPositiveVariable(): Boolean {
        if(numerator !is PositiveVariable) return false
        if(denominator !is PositiveVariable) return false

        return numerator != denominator
    }

    private fun Link.isInvolvingPositiveVariableAndPositiveDashOperation() =
            numerator is PositiveVariable
         && denominator is PositiveDashOperation

    private fun Link.isInvolvingPositiveVariableAndPositiveProduct() =
            numerator is PositiveVariable
         && denominator is PositiveProduct

    private fun Link.isInvolvingPositiveDashOperationAndNotPositiveOne(): Boolean {
        if(numerator !is PositiveDashOperation) return false
        if(denominator !is PositiveValue) return false

        return denominator != PositiveValue(1)
    }

    private fun Link.isInvolvingPositiveDashOperationAndPositiveVariable() =
            numerator is PositiveDashOperation
         && denominator is PositiveVariable

    private fun Link.isInvolvingPositiveDashOperationAndDifferentPositiveDashOperation(): Boolean {
        if(numerator !is PositiveDashOperation) return false
        if(denominator !is PositiveDashOperation) return false

        return numerator != denominator
    }

    private fun Link.isInvolvingPositiveDashOperationAndPositiveProduct() =
            numerator is PositiveDashOperation
         && denominator is PositiveProduct

    private fun Link.isInvolvingPositiveProductAndNotPositiveOne(): Boolean {
        if(numerator !is PositiveProduct) return false
        if(denominator !is PositiveValue) return false

        return denominator != PositiveValue(1)
    }

    private fun Link.isInvolvingPositiveProductAndPositiveVariable() =
            numerator is PositiveProduct
         && denominator is PositiveVariable

    private fun Link.isInvolvingPositiveProductAndPositiveDashOperation() =
            numerator is PositiveProduct
         && denominator is PositiveDashOperation

    private fun Link.isInvolvingPositiveProductAndDifferentPositiveProduct(): Boolean {
        if(numerator !is PositiveProduct) return false
        if(denominator !is PositiveProduct) return false

        return numerator != denominator
    }
}