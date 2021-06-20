package com.dg.eqs.classes

import com.dg.eqs.core.definition.term.item.variable.Variable


fun variable(unsignedName: String) = positiveVariable(unsignedName)

fun positiveVariable(unsignedName: String): Variable = TestVariable(unsignedName, false)

fun negativeVariable(unsignedName: String): Variable = TestVariable(unsignedName, true)

private class TestVariable(unsignedName: String, override val isNegative: Boolean)
    : Variable(unsignedName) {

    override fun copy() = TODO("not implemented")

    override fun invert() = TODO("not implemented")
}