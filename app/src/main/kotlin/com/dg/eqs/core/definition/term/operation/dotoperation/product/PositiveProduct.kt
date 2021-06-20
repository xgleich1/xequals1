package com.dg.eqs.core.definition.term.operation.dotoperation.product

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class PositiveProduct(operands: Terms) : Product(operands) {
    override val isNegative = false


    constructor(operands: List<Term>) : this(termsOf(operands))

    constructor(vararg operands: Term) : this(termsOf(*operands))

    override fun toString() = "+${super.toString()}"

    override fun invert() = NegativeProduct(operands)

    override fun recreate(newOperands: Terms) = PositiveProduct(newOperands)

    override fun applySign() = this
}