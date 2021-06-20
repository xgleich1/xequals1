package com.dg.eqs.core.definition.relation.greaterrelation.alteration.shiftingstep.wrapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingWrapper
import com.dg.eqs.core.definition.relation.greaterrelation.GreaterRelation
import com.dg.eqs.core.definition.term.Term


object GreaterRelationWrapper : ShiftingWrapper {
    override fun wrap(newLeft: Term, newRight: Term) = GreaterRelation(newLeft, newRight)
}