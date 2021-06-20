package com.dg.eqs.core.execution.intention.link.directshifting

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingEvent.*
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductExecutor


class DirectShiftingExecutor(
        private val directShiftingDetector: DirectShiftingDetector,
        private val shiftingOfEntireSideExecutor: ShiftingOfEntireSideExecutor,
        private val shiftingOutOfDashOperationExecutor: ShiftingOutOfDashOperationExecutor,
        private val shiftingOutOfDivisionExecutor: ShiftingOutOfDivisionExecutor,
        private val shiftingOutOfProductExecutor: ShiftingOutOfProductExecutor) {

    fun execute(link: Link) = when(directShiftingDetector.detect(link)) {
        ShiftingOfEntireSide -> shiftingOfEntireSideExecutor.execute(link)
        ShiftingOutOfDashOperation -> shiftingOutOfDashOperationExecutor.execute(link)
        ShiftingOutOfDivision -> shiftingOutOfDivisionExecutor.execute(link)
        ShiftingOutOfProduct -> shiftingOutOfProductExecutor.execute(link)
    }
}