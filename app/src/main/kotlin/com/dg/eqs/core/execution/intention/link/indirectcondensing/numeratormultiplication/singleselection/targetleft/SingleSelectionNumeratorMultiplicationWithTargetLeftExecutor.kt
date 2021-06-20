package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingMultiplicationWithVariableInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.InvalidSingleSelectionNumeratorMultiplication
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.ValidSingleSelectionNumeratorMultiplication


class SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor(
        private val detector: SingleSelectionNumeratorMultiplicationWithTargetLeftDetector,
        private val calculator: SingleSelectionNumeratorMultiplicationWithTargetLeftCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidSingleSelectionNumeratorMultiplication -> ValidStep(MultiplicationInfo, calculator.calculate(link))
        InvalidSingleSelectionNumeratorMultiplication -> InvalidStep(InvalidCondensingMultiplicationWithVariableInfo, link.origin)
    }
}