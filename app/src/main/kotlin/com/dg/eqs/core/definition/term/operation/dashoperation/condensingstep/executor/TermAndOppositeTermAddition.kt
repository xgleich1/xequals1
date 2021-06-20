package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor


object TermAndOppositeTermAddition : CondensingExecutor<Term, Term> {
    override fun execute(left: Term, right: Term) = termsOf(PositiveValue(0))
}