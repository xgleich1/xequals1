package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term


class DefaultRelationReplacing : RelationReplacing {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode() = javaClass.hashCode()

    override fun replace(relation: Relation, old: Term, new: Term) = with(relation) {
        when {
            old === left -> recreate(new, right)
            old === right -> recreate(left, new)

            else -> recreate(left.replace(old, new), right.replace(old, new))
        }
    }
}