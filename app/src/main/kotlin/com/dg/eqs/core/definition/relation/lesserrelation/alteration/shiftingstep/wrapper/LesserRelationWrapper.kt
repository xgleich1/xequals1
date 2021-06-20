package com.dg.eqs.core.definition.relation.lesserrelation.alteration.shiftingstep.wrapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingWrapper
import com.dg.eqs.core.definition.relation.lesserrelation.LesserRelation
import com.dg.eqs.core.definition.term.Term


object LesserRelationWrapper : ShiftingWrapper {
    override fun wrap(newLeft: Term, newRight: Term) = LesserRelation(newLeft, newRight)
}