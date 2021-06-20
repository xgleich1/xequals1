package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingMultiplicationWithVariableInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.InvalidSingleSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.ValidSingleSelectionCondensingMultiplication


class SingleSelectionCondensingInProductExecutor(
        private val detector: SingleSelectionCondensingInProductDetector,
        private val calculator: DirectCondensingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidSingleSelectionCondensingMultiplication -> ValidStep(MultiplicationInfo, calculator.calculate(link))
        InvalidSingleSelectionCondensingMultiplication -> InvalidStep(InvalidCondensingMultiplicationWithVariableInfo, link.origin)
    }
}