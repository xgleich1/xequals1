package com.dg.eqs.core.definition.term.item.variable


class PositiveVariable(unsignedName: String) : Variable(unsignedName) {
    override val isNegative = false


    override fun invert() = NegativeVariable(unsignedName)

    override fun copy() = PositiveVariable(unsignedName)
}