package com.dg.eqs.core.definition.term.operation.dotoperation.product

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import com.dg.eqs.core.definition.term.operation.dotoperation.DotOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.*


abstract class Product(operands: Terms) : DotOperation(operands) {
    override val alteration = OperationAlteration(
            ValueAndValueMultiplicationStep,
            ZeroAndAnythingMultiplicationStep,
            AnythingAndZeroMultiplicationStep,
            OneAndAnythingMultiplicationStep,
            AnythingAndOneMultiplicationStep,
            ProductAndProductMultiplicationStep,
            ProductAndAnythingMultiplicationStep,
            AnythingAndProductMultiplicationStep,
            DivisionAndDivisionMultiplicationStep,
            DivisionAndAnythingMultiplicationStep,
            AnythingAndDivisionMultiplicationStep,
            DashOperationAndDashOperationMultiplicationStep,
            DashOperationAndAnythingMultiplicationStep,
            AnythingAndDashOperationMultiplicationStep)


    override fun toString() = "*${super.toString()}"

    fun multiplyToFront(newOperands: Terms) = recreate(newOperands + operands)

    fun multiplyToBack(newOperands: Terms) = recreate(operands + newOperands)
}