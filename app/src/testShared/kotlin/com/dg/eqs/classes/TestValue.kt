package com.dg.eqs.classes

import com.dg.eqs.core.definition.term.item.value.Value


fun one() = value(1)

fun zero() = value(0)

fun positiveZero() = positiveValue(0)

fun negativeZero() = negativeValue(0)

fun value(unsignedNumber: Int) = positiveValue(unsignedNumber)

fun positiveValue(unsignedNumber: Int): Value = TestValue(unsignedNumber, false)

fun negativeValue(unsignedNumber: Int): Value = TestValue(unsignedNumber, true)

private class TestValue(unsignedNumber: Int, override val isNegative: Boolean)
    : Value(unsignedNumber) {

    override fun copy() = TODO("not implemented")

    override fun invert() = TODO("not implemented")
}