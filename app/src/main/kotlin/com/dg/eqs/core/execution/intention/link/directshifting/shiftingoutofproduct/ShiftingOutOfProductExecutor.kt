package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct

import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDivisionOfZeroInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.InvalidShiftingDivisionOfZeroOutOfProduct
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.ValidShiftingDivisionOutOfProduct
import com.dg.eqs.core.information.valid.DivisionInfo


class ShiftingOutOfProductExecutor(
        private val detector: ShiftingOutOfProductDetector,
        private val calculator: DirectShiftingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidShiftingDivisionOutOfProduct -> ValidStep(DivisionInfo, calculator.calculate(link))
        InvalidShiftingDivisionOfZeroOutOfProduct -> InvalidStep(InvalidShiftingDivisionOfZeroInfo, link.origin)
    }
}