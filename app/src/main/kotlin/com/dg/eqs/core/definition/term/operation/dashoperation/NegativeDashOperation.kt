package com.dg.eqs.core.definition.term.operation.dashoperation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class NegativeDashOperation(operands: Terms) : DashOperation(operands) {
    override val isNegative = true


    constructor(operands: List<Term>) : this(termsOf(operands))

    constructor(vararg operands: Term) : this(termsOf(*operands))

    override fun toString() = "-${super.toString()}"

    override fun invert() = PositiveDashOperation(operands)

    override fun recreate(newOperands: Terms) = NegativeDashOperation(newOperands)

    override fun applySign() = PositiveDashOperation(operands.mapInvert())

    override fun addToFront(newOperands: Terms) = recreate(newOperands.mapInvert() + operands)

    override fun addToBack(newOperands: Terms) = recreate(operands + newOperands.mapInvert())

    override fun subtractFromFront(newOperands: Terms) = recreate(newOperands + operands)

    override fun subtractFromBack(newOperands: Terms) = recreate(operands + newOperands)
}