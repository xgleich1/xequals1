package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationEvent.*


class MultiSelectionCondensingInDashOperationExecutor(
        private val detector: MultiSelectionCondensingInDashOperationDetector,
        private val calculator: DirectCondensingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidMultiSelectionCondensingAddition -> ValidStep(AdditionInfo, calculator.calculate(link))
        ValidMultiSelectionCondensingSubtraction -> ValidStep(SubtractionInfo, calculator.calculate(link))
        InvalidMultiSelectionCondensingAddition -> InvalidStep(InvalidCondensingInfo, link.origin)
        InvalidMultiSelectionCondensingSubtraction -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}