package com.dg.eqs.classes

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


fun division(numerator: Term, denominator: Term): Division = TestDivision(numerator, denominator)

private class TestDivision(numerator: Term, denominator: Term) : Division(numerator, denominator) {
    override val isNegative get() = TODO("not implemented")


    override fun invert() = TODO("not implemented")

    override fun recreate(newNumerator: Term, newDenominator: Term) = TODO("not implemented")

    override fun applySign() = TODO("not implemented")
}