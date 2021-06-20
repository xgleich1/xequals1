package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import com.dg.eqs.core.definition.term.Terms


interface CondensingMapper<L : CondensingSide, R : CondensingSide> {
    fun map(left: Terms, right: Terms): CondensingSides<L, R>
}