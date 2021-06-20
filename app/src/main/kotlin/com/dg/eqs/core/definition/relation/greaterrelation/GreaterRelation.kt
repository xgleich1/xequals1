package com.dg.eqs.core.definition.relation.greaterrelation

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.relation.alteration.RelationAlteration
import com.dg.eqs.core.definition.term.Term


class GreaterRelation(left: Term, right: Term) : Relation(left, right) {
    // When a greater relations sides are multiplied with -1
    // the greater relation changes to a lesser relation.
    // The multiplication with -1 can be detected using
    // source.negativeTermCount.isOdd and the counterpart
    // with source.negativeTermCount.isEven
    override val alteration = RelationAlteration()


    override fun toString() = ">${super.toString()}"

    override fun recreate(newLeft: Term, newRight: Term) = GreaterRelation(newLeft, newRight)
}