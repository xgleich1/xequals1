package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingMultiplicationWithVariableInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.InvalidSingleSelectionNumeratorMultiplication
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.ValidSingleSelectionNumeratorMultiplication


class SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor(
        private val detector: SingleSelectionNumeratorMultiplicationWithSourceLeftDetector,
        private val calculator: SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidSingleSelectionNumeratorMultiplication -> ValidStep(MultiplicationInfo, calculator.calculate(link))
        InvalidSingleSelectionNumeratorMultiplication -> InvalidStep(InvalidCondensingMultiplicationWithVariableInfo, link.origin)
    }
}