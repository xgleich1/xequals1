package com.dg.eqs.core.definition.term.operation.dotoperation.division

import com.dg.eqs.core.definition.term.Term


class PositiveDivision(numerator: Term, denominator: Term)
    : Division(numerator, denominator) {

    override val isNegative = false


    override fun toString() = "+${super.toString()}"

    override fun invert() = NegativeDivision(numerator, denominator)

    override fun recreate(newNumerator: Term, newDenominator: Term) =
            PositiveDivision(newNumerator, newDenominator)

    override fun applySign() = this
}