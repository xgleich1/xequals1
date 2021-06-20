package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term


interface ShiftingWrapper {
    fun wrap(newLeft: Term, newRight: Term) : Relation
}