package com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingWithdrawal
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


object OutOfSideWithdrawal : ShiftingWithdrawal<Term> {
    override fun withdraw(source: Terms, sourceSide: Term) =
            source.fold(sourceSide, Term::remove)
}