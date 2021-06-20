package com.dg.eqs.core.definition.term.item.value


class NegativeValue(unsignedNumber: Int) : Value(unsignedNumber) {
    override val isNegative = true


    override fun invert() = PositiveValue(unsignedNumber)

    override fun copy() = NegativeValue(unsignedNumber)
}