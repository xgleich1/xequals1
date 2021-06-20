package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation

import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingAdditionOutOfDashOperation
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationEvent.ShiftingSubtractionOutOfDashOperation


class ShiftingOutOfDashOperationDetector {
    private val Link.dashOperation
        get() = sourceParent as DashOperation


    fun detect(link: Link) = with(link) {
        if(isAdditionOutOfDashOperation()) {
            ShiftingAdditionOutOfDashOperation
        } else {
            ShiftingSubtractionOutOfDashOperation
        }
    }

    private fun Link.isAdditionOutOfDashOperation() =
            isAdditionOutOfPositiveDashOperation() ||
            isAdditionOutOfNegativeDashOperation()

    private fun Link.isAdditionOutOfPositiveDashOperation() =
            dashOperation.isPositive && firstSource.isNegative

    private fun Link.isAdditionOutOfNegativeDashOperation() =
            dashOperation.isNegative && firstSource.isPositive
}