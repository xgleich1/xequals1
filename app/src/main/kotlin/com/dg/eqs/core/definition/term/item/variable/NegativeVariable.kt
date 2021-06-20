package com.dg.eqs.core.definition.term.item.variable


class NegativeVariable(unsignedName: String) : Variable(unsignedName) {
    override val isNegative = true


    override fun invert() = PositiveVariable(unsignedName)

    override fun copy() = NegativeVariable(unsignedName)
}