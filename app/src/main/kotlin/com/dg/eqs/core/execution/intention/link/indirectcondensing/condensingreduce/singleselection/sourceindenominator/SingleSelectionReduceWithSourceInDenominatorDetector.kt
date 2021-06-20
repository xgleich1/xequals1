package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceDetector


class SingleSelectionReduceWithSourceInDenominatorDetector(
        private val singleSelectionReduceDetector: SingleSelectionReduceDetector) {

    fun detect(link: Link) = with(link) {
        singleSelectionReduceDetector.detect(firstTarget, firstSource)
    }
}