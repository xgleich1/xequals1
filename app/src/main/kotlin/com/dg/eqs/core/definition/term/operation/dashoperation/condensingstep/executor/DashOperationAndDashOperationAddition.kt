package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object DashOperationAndDashOperationAddition
    : CondensingExecutor<DashOperation, DashOperation> {

    override fun execute(left: DashOperation, right: DashOperation) =
            termsOf(left.addToBack(right.applySign().operands))
}