package com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing

import com.dg.eqs.base.extension.all
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link


class InvalidCondensingDueToBracketingDecider {
    fun decide(link: Link) = with(link) {
        parentsChain.all<DashOperation>() || parentsChain.all<Product>()
    }
}