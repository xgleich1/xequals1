package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.abbreviation.valueOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.google.common.math.IntMath.gcd


object ValueAndValueWithGcdGreaterOneDivision : CondensingExecutor<Value, Value> {
    override fun execute(left: Value, right: Value): Terms {
        val unsignedResult = divideUnsigned(left, right)

        return termsOf(unsignedResult.toSignedResult(left, right))
    }

    private fun divideUnsigned(numerator: Value, denominator: Value): Term {
        val gcd = gcd(numerator.unsignedNumber, denominator.unsignedNumber)

        val reducedNumerator = valueOf(numerator.unsignedNumber / gcd)
        val reducedDenominator = valueOf(denominator.unsignedNumber / gcd)

        return if(reducedDenominator.isOne) {
            reducedNumerator
        } else {
            PositiveDivision(reducedNumerator, reducedDenominator)
        }
    }

    private fun Term.toSignedResult(numerator: Value, denominator: Value) =
            if(numerator.isPositive == denominator.isPositive) this else invert()
}