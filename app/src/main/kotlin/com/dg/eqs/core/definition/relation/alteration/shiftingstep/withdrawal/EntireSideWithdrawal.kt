package com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingWithdrawal
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue


object EntireSideWithdrawal : ShiftingWithdrawal<Term> {
    override fun withdraw(source: Terms, sourceSide: Term) = PositiveValue(0)
}