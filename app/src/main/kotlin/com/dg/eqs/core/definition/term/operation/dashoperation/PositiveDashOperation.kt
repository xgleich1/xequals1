package com.dg.eqs.core.definition.term.operation.dashoperation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class PositiveDashOperation(operands: Terms) : DashOperation(operands) {
    override val isNegative = false


    constructor(operands: List<Term>) : this(termsOf(operands))

    constructor(vararg operands: Term) : this(termsOf(*operands))

    override fun toString() = "+${super.toString()}"

    override fun invert() = NegativeDashOperation(operands)

    override fun recreate(newOperands: Terms) = PositiveDashOperation(newOperands)

    override fun applySign() = this

    override fun addToFront(newOperands: Terms) = recreate(newOperands + operands)

    override fun addToBack(newOperands: Terms) = recreate(operands + newOperands)

    override fun subtractFromFront(newOperands: Terms) = recreate(newOperands.mapInvert() + operands)

    override fun subtractFromBack(newOperands: Terms) = recreate(operands + newOperands.mapInvert())
}