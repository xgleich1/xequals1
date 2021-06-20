package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


interface ShiftingWithdrawal<in S : Term> {
    fun withdraw(source: Terms, sourceSide: S): Term
}