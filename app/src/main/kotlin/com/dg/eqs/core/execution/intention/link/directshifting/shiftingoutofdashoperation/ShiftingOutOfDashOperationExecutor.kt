package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingAdditionOutOfDashOperation
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingSubtractionOutOfDashOperation
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo


class ShiftingOutOfDashOperationExecutor(
        private val detector: ShiftingOutOfDashOperationDetector,
        private val calculator: DirectShiftingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ShiftingAdditionOutOfDashOperation -> ValidStep(AdditionInfo, calculator.calculate(link))
        ShiftingSubtractionOutOfDashOperation -> ValidStep(SubtractionInfo, calculator.calculate(link))
    }
}