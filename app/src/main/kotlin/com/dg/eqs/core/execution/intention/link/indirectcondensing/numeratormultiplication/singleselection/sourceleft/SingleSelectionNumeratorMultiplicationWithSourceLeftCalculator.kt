package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationCalculator


class SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator(
        private val singleSelectionNumeratorMultiplicationCalculator: SingleSelectionNumeratorMultiplicationCalculator) {

    fun calculate(link: Link) = with(link) {
        singleSelectionNumeratorMultiplicationCalculator.calculate(link, firstSource, firstTarget)
    }
}