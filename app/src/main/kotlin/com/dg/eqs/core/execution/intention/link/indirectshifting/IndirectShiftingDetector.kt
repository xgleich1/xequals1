package com.dg.eqs.core.execution.intention.link.indirectshifting

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting.InvalidShiftingDueToBracketingDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting.InvalidShiftingDueToOrderOfOperationsDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionDecider


class IndirectShiftingDetector(
        private val indirectShiftingOutOfDivisionDecider: IndirectShiftingOutOfDivisionDecider,
        private val invalidShiftingDueToOrderOfOperationsDecider: InvalidShiftingDueToOrderOfOperationsDecider,
        private val invalidShiftingDueToBracketingDecider: InvalidShiftingDueToBracketingDecider) {

    fun detect(link: Link) = when {
        indirectShiftingOutOfDivisionDecider.decide(link) -> IndirectShiftingOutOfDivision
        invalidShiftingDueToOrderOfOperationsDecider.decide(link) -> InvalidIndirectShiftingDueToOrderOfOperations
        invalidShiftingDueToBracketingDecider.decide(link) -> InvalidIndirectShiftingDueToBracketing

        else -> InvalidIndirectShifting
    }
}