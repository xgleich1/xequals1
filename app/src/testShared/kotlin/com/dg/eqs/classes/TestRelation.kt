package com.dg.eqs.classes

import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.definition.term.Term


fun relation(left: Term, right: Term): Relation = TestRelation(left, right)

private class TestRelation(left: Term, right: Term) : Relation(left, right) {
    override val alteration get() = TODO("not implemented")


    override fun recreate(newLeft: Term, newRight: Term) = TODO("not implemented")
}