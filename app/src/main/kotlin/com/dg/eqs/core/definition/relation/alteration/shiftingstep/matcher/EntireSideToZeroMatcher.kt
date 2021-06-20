package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMatcher
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isZero


object EntireSideToZeroMatcher : ShiftingMatcher {
    override fun matches(sourceSide: Term, targetSide: Term, source: Terms): Boolean {
        if(!targetSide.isZero) return false

        return sourceSide in source
    }
}