package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft.SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft.SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor


class NumeratorMultiplicationExecutor(
        private val numeratorMultiplicationDetector: NumeratorMultiplicationDetector,
        private val singleSelectionNumeratorMultiplicationWithSourceLeftExecutor: SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor,
        private val singleSelectionNumeratorMultiplicationWithTargetLeftExecutor: SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor) {

    fun execute(link: Link) = when(numeratorMultiplicationDetector.detect(link)) {
        SingleSelectionNumeratorMultiplicationWithSourceLeft -> singleSelectionNumeratorMultiplicationWithSourceLeftExecutor.execute(link)
        SingleSelectionNumeratorMultiplicationWithTargetLeft -> singleSelectionNumeratorMultiplicationWithTargetLeftExecutor.execute(link)
        MultiSelectionNumeratorMultiplicationWithSourceLeft -> InvalidStep(InvalidCondensingInfo, link.origin)
        MultiSelectionNumeratorMultiplicationWithTargetLeft -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}