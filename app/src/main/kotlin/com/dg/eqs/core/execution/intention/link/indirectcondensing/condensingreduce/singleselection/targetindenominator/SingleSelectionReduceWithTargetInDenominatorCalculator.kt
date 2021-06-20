package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceCalculator


class SingleSelectionReduceWithTargetInDenominatorCalculator(
        private val singleSelectionReduceCalculator: SingleSelectionReduceCalculator) {

    fun calculate(link: Link) = with(link) {
        singleSelectionReduceCalculator.calculate(origin, firstSource, firstTarget)
    }
}