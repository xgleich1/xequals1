package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object AnythingAndDashOperationAddition : CondensingExecutor<Terms, DashOperation> {
    override fun execute(left: Terms, right: DashOperation) = termsOf(right.addToFront(left))
}