package com.dg.eqs.core.definition.term.operation.dotoperation.division

import com.dg.eqs.core.definition.term.Term


class NegativeDivision(numerator: Term, denominator: Term)
    : Division(numerator, denominator) {

    override val isNegative = true


    override fun toString() = "-${super.toString()}"

    override fun invert() = PositiveDivision(numerator, denominator)

    override fun recreate(newNumerator: Term, newDenominator: Term) =
            NegativeDivision(newNumerator, newDenominator)

    override fun applySign() = PositiveDivision(numerator.invert(), denominator)
}