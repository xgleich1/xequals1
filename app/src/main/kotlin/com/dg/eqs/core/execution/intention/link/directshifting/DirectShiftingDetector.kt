package com.dg.eqs.core.execution.intention.link.directshifting

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingEvent.*


class DirectShiftingDetector {
    fun detect(link: Link) = when(link.sourceParent) {
        is Relation -> ShiftingOfEntireSide
        is DashOperation -> ShiftingOutOfDashOperation
        is Division -> ShiftingOutOfDivision
        is Product -> ShiftingOutOfProduct

        else -> throw IllegalArgumentException()
    }
}