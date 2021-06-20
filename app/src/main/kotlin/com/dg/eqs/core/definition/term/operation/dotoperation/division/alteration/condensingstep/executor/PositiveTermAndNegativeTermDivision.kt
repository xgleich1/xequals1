package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingExecutor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision


object PositiveTermAndNegativeTermDivision : CondensingExecutor<Term, Term> {
    override fun execute(left: Term, right: Term) = termsOf(
            NegativeDivision(left, right.invert()))
}