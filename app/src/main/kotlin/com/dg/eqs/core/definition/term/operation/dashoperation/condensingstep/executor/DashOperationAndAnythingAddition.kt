package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object DashOperationAndAnythingAddition : CondensingExecutor<DashOperation, Terms> {
    override fun execute(left: DashOperation, right: Terms) = termsOf(left.addToBack(right))
}