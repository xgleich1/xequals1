package com.dg.eqs.core.definition.term.operation.dotoperation.product

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue


class NegativeProduct(operands: Terms) : Product(operands) {
    override val isNegative = true


    constructor(operands: List<Term>) : this(termsOf(operands))

    constructor(vararg operands: Term) : this(termsOf(*operands))

    override fun toString() = "-${super.toString()}"

    override fun invert() = PositiveProduct(operands)

    override fun recreate(newOperands: Terms) = NegativeProduct(newOperands)

    override fun applySign() = PositiveProduct(termsOf(NegativeValue(1), operands))
}