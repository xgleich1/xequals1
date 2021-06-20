package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


interface ShiftingMatcher {
    fun matches(sourceSide: Term, targetSide: Term, source: Terms): Boolean
}