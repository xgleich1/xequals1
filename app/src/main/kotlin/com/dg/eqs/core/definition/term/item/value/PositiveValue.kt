package com.dg.eqs.core.definition.term.item.value


class PositiveValue(unsignedNumber: Int) : Value(unsignedNumber) {
    override val isNegative = false


    override fun invert() = NegativeValue(unsignedNumber)

    override fun copy() = PositiveValue(unsignedNumber)
}