package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingAdditionWithVariableInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingSubtractionWithVariableInfo
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationEvent.*


class SingleSelectionCondensingInDashOperationExecutor(
        private val detector: SingleSelectionCondensingInDashOperationDetector,
        private val calculator: DirectCondensingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidSingleSelectionCondensingAddition -> ValidStep(AdditionInfo, calculator.calculate(link))
        ValidSingleSelectionCondensingSubtraction -> ValidStep(SubtractionInfo, calculator.calculate(link))
        InvalidSingleSelectionCondensingAddition -> InvalidStep(InvalidCondensingAdditionWithVariableInfo, link.origin)
        InvalidSingleSelectionCondensingSubtraction -> InvalidStep(InvalidCondensingSubtractionWithVariableInfo, link.origin)
    }
}