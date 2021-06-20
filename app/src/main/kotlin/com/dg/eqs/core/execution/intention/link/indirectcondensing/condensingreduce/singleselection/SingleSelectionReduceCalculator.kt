package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision


class SingleSelectionReduceCalculator {
    fun calculate(origin: AnyOrigin, numerator: Term, denominator: Term) =
            when(val condensation = divide(numerator, denominator)) {
                is Division -> {
                    val (exemptedNumerator, exemptedDenominator) =
                            condensation.applySign()

                    origin.replace(numerator, exemptedNumerator)
                          .replace(denominator, exemptedDenominator)
                }

                PositiveValue(1) -> origin
                        .remove(numerator)
                        .remove(denominator)

                else -> origin
                        .remove(denominator)
                        .replace(numerator, condensation)
            }

    private fun divide(numerator: Term, denominator: Term) =
            PositiveDivision(numerator, denominator).condense()
}