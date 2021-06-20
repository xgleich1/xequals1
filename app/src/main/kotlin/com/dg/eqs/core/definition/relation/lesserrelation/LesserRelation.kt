package com.dg.eqs.core.definition.relation.lesserrelation

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.relation.alteration.RelationAlteration
import com.dg.eqs.core.definition.term.Term


class LesserRelation(left: Term, right: Term) : Relation(left, right) {
    override val alteration = RelationAlteration()


    override fun toString() = "<${super.toString()}"

    override fun recreate(newLeft: Term, newRight: Term) = LesserRelation(newLeft, newRight)
}