package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMatcher
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object OutOfPositiveDashOperationToAnythingMatcher : ShiftingMatcher {
    override fun matches(sourceSide: Term, targetSide: Term, source: Terms): Boolean {
        if(sourceSide !is PositiveDashOperation) return false

        return source in sourceSide
    }
}