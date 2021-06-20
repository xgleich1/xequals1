package com.dg.eqs.core.execution.intention.link.indirectshifting

import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDueToBracketingInfo
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingDueToOrderOfOperationsInfo
import com.dg.eqs.core.information.invalid.shifting.InvalidShiftingInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionExecutor


class IndirectShiftingExecutor(
        private val indirectShiftingDetector: IndirectShiftingDetector,
        private val indirectShiftingOutOfDivisionExecutor: IndirectShiftingOutOfDivisionExecutor) {

    fun execute(link: Link) = when(indirectShiftingDetector.detect(link)) {
        IndirectShiftingOutOfDivision -> indirectShiftingOutOfDivisionExecutor.execute(link)
        InvalidIndirectShiftingDueToOrderOfOperations -> InvalidStep(InvalidShiftingDueToOrderOfOperationsInfo, link.origin)
        InvalidIndirectShiftingDueToBracketing -> InvalidStep(InvalidShiftingDueToBracketingInfo, link.origin)
        InvalidIndirectShifting -> InvalidStep(InvalidShiftingInfo, link.origin)
    }
}