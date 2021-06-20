package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndTermMapper
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.PositiveTermAndNegativeTermDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.extractor.TermAndTermFromDivisionExtractor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher.PositiveTermAndNegativeTermInDivisionMatcher


object PositiveTermAndNegativeTermDivisionStep : CondensingStep<Term, Term>(
        PositiveTermAndNegativeTermInDivisionMatcher,
        TermAndTermFromDivisionExtractor,
        TermAndTermMapper,
        PositiveTermAndNegativeTermDivision)