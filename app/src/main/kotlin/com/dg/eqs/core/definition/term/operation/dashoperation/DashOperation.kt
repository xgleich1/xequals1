package com.dg.eqs.core.definition.term.operation.dashoperation

import com.dg.eqs.base.extension.first
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.*


abstract class DashOperation(operands: Terms) : Operation(operands) {
    override val alteration = OperationAlteration(
            ValueAndValueAdditionStep,
            ZeroAndAnythingAdditionStep,
            AnythingAndZeroAdditionStep,
            TermAndOppositeTermAdditionStep,
            DashOperationAndDashOperationAdditionStep,
            DashOperationAndAnythingAdditionStep,
            AnythingAndDashOperationAdditionStep,
            DivisionAndEqualDenominatorDivisionAdditionStep,
            DivisionAndDivisionAdditionStep,
            DivisionThroughPositiveOneAndAnythingAdditionStep,
            AnythingAndDivisionThroughPositiveOneAdditionStep,
            DivisionAndAnythingAdditionStep,
            AnythingAndDivisionAdditionStep,
            TermAndEqualTermAdditionStep)


    override fun toString() = "±${super.toString()}"

    fun isAddition(source: Terms, target: Terms): Boolean {
        require(source in operands)
        require(target in operands)

        return getDecidingTerm(source, target).isPositive
    }

    abstract fun addToFront(newOperands: Terms): DashOperation

    abstract fun addToBack(newOperands: Terms): DashOperation

    abstract fun subtractFromFront(newOperands: Terms): DashOperation

    abstract fun subtractFromBack(newOperands: Terms): DashOperation

    private fun getDecidingTerm(source: Terms, target: Terms) = with(operands) {
        val firstSource = source.first
        val firstTarget = target.first

        if(indexOf(firstSource) < indexOf(firstTarget)) firstTarget else firstSource
    }
}