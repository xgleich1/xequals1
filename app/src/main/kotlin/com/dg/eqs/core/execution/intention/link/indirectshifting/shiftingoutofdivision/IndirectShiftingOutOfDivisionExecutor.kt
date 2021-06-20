package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionEvent.*
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDivisionOfZeroInfo
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingMultiplicationOfZeroInfo
import com.dg.eqs.core.information.valid.DivisionInfo
import com.dg.eqs.core.information.valid.MultiplicationInfo


class IndirectShiftingOutOfDivisionExecutor(
        private val detector: IndirectShiftingOutOfDivisionDetector,
        private val calculator: IndirectShiftingOutOfDivisionCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidIndirectShiftingDivisionOutOfDivision -> ValidStep(DivisionInfo, calculator.calculate(link))
        ValidIndirectShiftingMultiplicationOutOfDivision -> ValidStep(MultiplicationInfo, calculator.calculate(link))
        InvalidIndirectShiftingDivisionOfZeroOutOfDivision -> InvalidStep(InvalidShiftingDivisionOfZeroInfo, link.origin)
        InvalidIndirectShiftingMultiplicationOfZeroOutOfDivision -> InvalidStep(InvalidShiftingMultiplicationOfZeroInfo, link.origin)
    }
}