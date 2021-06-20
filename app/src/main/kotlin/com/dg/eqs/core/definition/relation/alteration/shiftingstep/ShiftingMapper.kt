package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term


interface ShiftingMapper<S : Term, T : Term> {
    fun map(sourceSide: Term, targetSide: Term): ShiftingSides<S, T>
}