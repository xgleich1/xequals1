package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingWrapper
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.definition.term.Term


object EqualsRelationWrapper : ShiftingWrapper {
    override fun wrap(newLeft: Term, newRight: Term) = EqualsRelation(newLeft, newRight)
}