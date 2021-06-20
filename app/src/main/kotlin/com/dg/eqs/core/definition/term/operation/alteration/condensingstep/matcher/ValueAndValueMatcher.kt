package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMatcher


object ValueAndValueMatcher : CondensingMatcher {
    override fun matches(operands: Terms) =
            operands.filterIsInstance<Value>().size >= 2

    override fun matches(left: Terms, right: Terms) =
            left.isMonad<Value>() && right.isMonad<Value>()
}