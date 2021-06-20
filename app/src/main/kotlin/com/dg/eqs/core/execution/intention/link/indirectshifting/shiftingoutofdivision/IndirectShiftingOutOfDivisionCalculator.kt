package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.execution.intention.Intention.Link


class IndirectShiftingOutOfDivisionCalculator {
    private val Link.relation
        get() = origin as Relation


    fun calculate(link: Link) = with(link) {
        relation.shift(source, firstTarget)
    }
}