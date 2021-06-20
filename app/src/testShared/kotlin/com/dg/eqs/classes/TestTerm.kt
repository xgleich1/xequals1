package com.dg.eqs.classes

import com.dg.eqs.core.definition.term.Term


fun term() = positiveTerm()

fun positiveTerm(): Term = TestTerm(false)

fun negativeTerm(): Term = TestTerm(true)

private class TestTerm(override val isNegative: Boolean) : Term() {
    override fun toString() = "${if(isNegative) "Negative" else "Positive"}Term"

    override fun isSolved() = TODO("not implemented")

    override fun isConstant() = TODO("not implemented")

    override fun remove(term: Term) = TODO("not implemented")

    override fun replace(old: Term, new: Term) = TODO("not implemented")

    override fun invert() = TODO("not implemented")

    override fun copy() = TODO("not implemented")

    override fun resolve() = TODO("not implemented")
}