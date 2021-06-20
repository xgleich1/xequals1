package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object ZeroAndAnythingAddition : CondensingExecutor<Terms, Terms> {
    override fun execute(left: Terms, right: Terms) = right
}