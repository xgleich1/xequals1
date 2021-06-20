package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceDetector


class SingleSelectionReduceWithTargetInDenominatorDetector(
        private val singleSelectionReduceDetector: SingleSelectionReduceDetector) {

    fun detect(link: Link) = with(link) {
        singleSelectionReduceDetector.detect(firstSource, firstTarget)
    }
}