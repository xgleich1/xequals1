package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object AnythingAndZeroMultiplication : CondensingExecutor<Terms, Terms> {
    override fun execute(left: Terms, right: Terms) = termsOf(PositiveValue(0))
}