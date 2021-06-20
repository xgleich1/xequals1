package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object TermAndOppositeTermDivision : CondensingExecutor<Term, Term> {
    override fun execute(left: Term, right: Term) = termsOf(NegativeValue(1))
}