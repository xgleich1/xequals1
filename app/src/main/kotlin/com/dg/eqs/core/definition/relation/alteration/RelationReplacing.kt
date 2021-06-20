package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term


interface RelationReplacing {
    fun replace(relation: Relation, old: Term, new: Term): Relation
}