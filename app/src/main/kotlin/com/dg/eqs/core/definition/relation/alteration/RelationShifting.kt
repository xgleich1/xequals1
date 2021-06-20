package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


interface RelationShifting {
    fun shift(relation: Relation, source: Terms, targetSide: Term): Relation
}