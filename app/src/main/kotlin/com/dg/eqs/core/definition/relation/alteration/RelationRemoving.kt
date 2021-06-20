package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term


interface RelationRemoving {
    fun remove(relation: Relation, term: Term): Relation
}