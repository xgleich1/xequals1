package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.base.extension.isTriad
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link


class IndirectShiftingOutOfDivisionDecider {
    fun decide(link: Link) = with(link) {
        parentsChain.isTriad<Product, Division, Relation>()
    }
}