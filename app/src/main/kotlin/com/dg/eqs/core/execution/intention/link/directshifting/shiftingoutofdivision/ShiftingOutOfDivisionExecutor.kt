package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionEvent.*
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDivisionOfZeroInfo
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingMultiplicationOfZeroInfo
import com.dg.eqs.core.information.valid.DivisionInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo


class ShiftingOutOfDivisionExecutor(
        private val detector: ShiftingOutOfDivisionDetector,
        private val calculator: DirectShiftingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidShiftingDivisionOutOfDivision -> ValidStep(DivisionInfo, calculator.calculate(link))
        ValidShiftingMultiplicationOutOfDivision -> ValidStep(MultiplicationInfo, calculator.calculate(link))
        InvalidShiftingDivisionOfZeroOutOfDivision -> InvalidStep(InvalidShiftingDivisionOfZeroInfo, link.origin)
        InvalidShiftingMultiplicationOfZeroOutOfDivision -> InvalidStep(InvalidShiftingMultiplicationOfZeroInfo, link.origin)
    }
}