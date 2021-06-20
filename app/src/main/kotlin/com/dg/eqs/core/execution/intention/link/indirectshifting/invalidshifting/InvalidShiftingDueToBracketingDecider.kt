package com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting

import com.dg.eqs.base.extension.all
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link


class InvalidShiftingDueToBracketingDecider {
    private val Link.relation get() = mutualParent


    fun decide(link: Link) = with(link) {
        val parentsChainWithoutRelation = parentsChain - relation

        parentsChainWithoutRelation.all<DashOperation>() ||
        parentsChainWithoutRelation.all<Product>()
    }
}