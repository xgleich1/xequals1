package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.base.abbreviation.AnyShiftingStep
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class RelationAlteration(
        private val removing: RelationRemoving,
        private val replacing: RelationReplacing,
        private val shifting: RelationShifting) {

    constructor(vararg steps: AnyShiftingStep) : this(
            DefaultRelationRemoving(),
            DefaultRelationReplacing(),
            DefaultRelationShifting(*steps))

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as RelationAlteration

        if(removing != other.removing) return false
        if(replacing != other.replacing) return false
        if(shifting != other.shifting) return false

        return true
    }

    override fun hashCode(): Int {
        var result = removing.hashCode()

        result = 31 * result + replacing.hashCode()
        result = 31 * result + shifting.hashCode()

        return result
    }

    fun remove(relation: Relation, term: Term) =
            removing.remove(relation, term)

    fun replace(relation: Relation, old: Term, new: Term) =
            replacing.replace(relation, old, new)

    fun shift(relation: Relation, source: Terms, targetSide: Term) =
            shifting.shift(relation, source, targetSide)
}