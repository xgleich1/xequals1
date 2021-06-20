package com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingAdditionOfEntireSide
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideEvent.ShiftingSubtractionOfEntireSide


class ShiftingOfEntireSideDetector {
    fun detect(link: Link) = with(link) {
        if(firstSource.isNegative) {
            ShiftingAdditionOfEntireSide
        } else {
            ShiftingSubtractionOfEntireSide
        }
    }
}