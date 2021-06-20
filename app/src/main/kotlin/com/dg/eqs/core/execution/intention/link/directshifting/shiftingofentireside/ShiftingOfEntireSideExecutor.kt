package com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingAdditionOfEntireSide
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingSubtractionOfEntireSide
import com.dg.eqs.core.information.valid.AdditionInfo
import com.dg.eqs.core.information.valid.SubtractionInfo


class ShiftingOfEntireSideExecutor(
        private val detector: ShiftingOfEntireSideDetector,
        private val calculator: DirectShiftingCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ShiftingAdditionOfEntireSide -> ValidStep(AdditionInfo, calculator.calculate(link))
        ShiftingSubtractionOfEntireSide -> ValidStep(SubtractionInfo, calculator.calculate(link))
    }
}