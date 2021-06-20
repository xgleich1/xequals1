package com.dg.eqs.classes

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


fun dashOperation(vararg operands: Term): DashOperation = TestDashOperation(termsOf(*operands))

private class TestDashOperation(operands: Terms) : DashOperation(operands) {
    override val isNegative get() = TODO("not implemented")


    override fun invert() = TODO("not implemented")

    override fun recreate(newOperands: Terms) = TODO("not implemented")

    override fun applySign() = TODO("not implemented")

    override fun addToFront(newOperands: Terms) = TODO("not implemented")

    override fun addToBack(newOperands: Terms) = TODO("not implemented")

    override fun subtractFromFront(newOperands: Terms) = TODO("not implemented")

    override fun subtractFromBack(newOperands: Terms) = TODO("not implemented")
}