package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.InvalidMultiSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.ValidMultiSelectionCondensingMultiplication


class MultiSelectionCondensingInProductExecutor(
        private val detector: MultiSelectionCondensingInProductDetector,
        private val calculator: DirectCondensingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidMultiSelectionCondensingMultiplication -> ValidStep(MultiplicationInfo, calculator.calculate(link))
        InvalidMultiSelectionCondensingMultiplication -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}