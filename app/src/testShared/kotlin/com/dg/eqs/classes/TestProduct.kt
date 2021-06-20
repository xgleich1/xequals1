package com.dg.eqs.classes

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


fun product(vararg operands: Term) = positiveProduct(*operands)

fun positiveProduct(vararg operands: Term): Product = TestProduct(termsOf(*operands), false)

fun negativeProduct(vararg operands: Term): Product = TestProduct(termsOf(*operands), true)

private class TestProduct(operands: Terms, override val isNegative: Boolean) : Product(operands) {
    override fun invert() = TODO("not implemented")

    override fun recreate(newOperands: Terms) = TODO("not implemented")

    override fun applySign() = TODO("not implemented")
}