package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationDetector


class SingleSelectionNumeratorMultiplicationWithSourceLeftDetector(
        private val singleSelectionNumeratorMultiplicationDetector: SingleSelectionNumeratorMultiplicationDetector) {

    fun detect(link: Link) = with(link) {
        singleSelectionNumeratorMultiplicationDetector.detect(firstSource, firstTarget)
    }
}