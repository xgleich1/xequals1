package com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting

import com.dg.eqs.base.extension.any
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.DotOperation
import com.dg.eqs.core.execution.intention.Intention.Link


class InvalidShiftingDueToOrderOfOperationsDecider {
    private val Link.relation get() = mutualParent


    fun decide(link: Link) = with(link) {
        val parentsChainWithoutRelation = parentsChain - relation

        parentsChainWithoutRelation.any<DashOperation>() &&
        parentsChainWithoutRelation.any<DotOperation>()
    }
}