package com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing

import com.dg.eqs.base.extension.any
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.DotOperation
import com.dg.eqs.core.execution.intention.Intention.Link


class InvalidCondensingDueToOrderOfOperationsDecider {
    fun decide(link: Link) = with(link) {
        parentsChain.any<DashOperation>() && parentsChain.any<DotOperation>()
    }
}