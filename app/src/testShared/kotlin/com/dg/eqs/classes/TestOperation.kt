package com.dg.eqs.classes

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation


fun operation(vararg operands: Term) = positiveOperation(*operands)

fun positiveOperation(vararg operands: Term): Operation = TestOperation(termsOf(*operands), false)

fun negativeOperation(vararg operands: Term): Operation = TestOperation(termsOf(*operands), true)

private class TestOperation(operands: Terms, override val isNegative: Boolean) : Operation(operands) {
    override val alteration get() = TODO("not implemented")


    override fun invert() = TODO("not implemented")

    override fun recreate(newOperands: Terms) = TestOperation(newOperands, isNegative)

    override fun applySign() = TODO("not implemented")
}