package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue


class DefaultRelationRemoving : RelationRemoving {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode() = javaClass.hashCode()

    override fun remove(relation: Relation, term: Term) = with(relation) {
        when {
            term === left -> recreate(PositiveValue(0), right)
            term === right -> recreate(left, PositiveValue(0))

            else -> recreate(left.remove(term), right.remove(term))
        }
    }
}