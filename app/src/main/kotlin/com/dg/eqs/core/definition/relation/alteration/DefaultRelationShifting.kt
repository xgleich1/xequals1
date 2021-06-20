package com.dg.eqs.core.definition.relation.alteration

import com.dg.eqs.base.abbreviation.AnyShiftingStep
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class DefaultRelationShifting(
        private vararg val steps: AnyShiftingStep) : RelationShifting {

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as DefaultRelationShifting

        if(!steps.contentEquals(other.steps)) return false

        return true
    }

    override fun hashCode() = steps.contentHashCode()

    override fun shift(relation: Relation, source: Terms, targetSide: Term): Relation {
        val step = checkNotNull(findStep(relation, source, targetSide)) {
            "Cannot shift $source to $targetSide in $relation"
        }

        return step.apply(relation, source, targetSide)
    }

    private fun findStep(relation: Relation, source: Terms, targetSide: Term) =
            steps.find { it.isApplicable(relation, source, targetSide) }
}