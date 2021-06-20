package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMatcher
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object OutOfProductAsNumeratorOfDivisionToAnythingMatcher : ShiftingMatcher {
    override fun matches(sourceSide: Term, targetSide: Term, source: Terms): Boolean {
        if(sourceSide !is Division) return false

        val numerator = sourceSide.numerator as? Product
                ?: return false

        return source in numerator
    }
}