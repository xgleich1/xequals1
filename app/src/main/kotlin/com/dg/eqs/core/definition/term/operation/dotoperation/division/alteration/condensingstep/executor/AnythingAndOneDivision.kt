package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object AnythingAndOneDivision : CondensingExecutor<Term, Value> {
    override fun execute(left: Term, right: Value) = termsOf(
            if(right.isPositive) left else left.invert())
}