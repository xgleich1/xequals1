package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration

import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.alteration.OperandsRemoving


class DivisionOperandsRemoving : OperandsRemoving {
    private val Terms.numerator get() = first
    private val Terms.denominator get() = second


    override fun remove(operands: Terms, term: Term) = with(operands) {
        when {
            term === numerator -> removeNumerator()
            term === denominator -> removeDenominator()

            else -> mapRemove(term)
        }
    }

    private fun Terms.removeNumerator() = replace(numerator, PositiveValue(1))

    private fun Terms.removeDenominator() = remove(denominator)
}