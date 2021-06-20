package com.dg.eqs.classes

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.DotOperation


fun dotOperation(vararg operands: Term): DotOperation = TestDotOperation(termsOf(*operands))

private class TestDotOperation(operands: Terms) : DotOperation(operands) {
    override val isNegative get() = TODO("not implemented")

    override val alteration get() = TODO("not implemented")


    override fun invert() = TODO("not implemented")

    override fun recreate(newOperands: Terms) = TODO("not implemented")

    override fun applySign() = TODO("not implemented")
}