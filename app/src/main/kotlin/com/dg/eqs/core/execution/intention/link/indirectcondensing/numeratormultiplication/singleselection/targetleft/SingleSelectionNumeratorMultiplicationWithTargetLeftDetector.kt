package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationDetector


class SingleSelectionNumeratorMultiplicationWithTargetLeftDetector(
        private val singleSelectionNumeratorMultiplicationDetector: SingleSelectionNumeratorMultiplicationDetector) {

    fun detect(link: Link) = with(link) {
        singleSelectionNumeratorMultiplicationDetector.detect(firstTarget, firstSource)
    }
}