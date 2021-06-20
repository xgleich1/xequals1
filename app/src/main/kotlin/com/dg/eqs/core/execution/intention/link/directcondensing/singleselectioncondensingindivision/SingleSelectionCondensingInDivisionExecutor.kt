package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDivisionThroughZeroInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.DivisionInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionEvent.*


class SingleSelectionCondensingInDivisionExecutor(
        private val detector: SingleSelectionCondensingInDivisionDetector,
        private val calculator: DirectCondensingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidSingleSelectionCondensingDivision -> ValidStep(DivisionInfo, calculator.calculate(link))
        InvalidSingleSelectionCondensingDivisionThroughZero -> InvalidStep(InvalidCondensingDivisionThroughZeroInfo, link.origin)
        InvalidSingleSelectionCondensingDivision -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}