package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.abbreviation.valueOf
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object ValueAndValueAddition : CondensingExecutor<Value, Value> {
    override fun execute(left: Value, right: Value) = termsOf(
            valueOf(left.signedNumber + right.signedNumber))
}