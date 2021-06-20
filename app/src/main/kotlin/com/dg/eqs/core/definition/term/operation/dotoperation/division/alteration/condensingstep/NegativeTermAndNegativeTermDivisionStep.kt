package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndTermMapper
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.NegativeTermAndNegativeTermDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.extractor.TermAndTermFromDivisionExtractor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher.NegativeTermAndNegativeTermInDivisionMatcher


object NegativeTermAndNegativeTermDivisionStep : CondensingStep<Term, Term>(
        NegativeTermAndNegativeTermInDivisionMatcher,
        TermAndTermFromDivisionExtractor,
        TermAndTermMapper,
        NegativeTermAndNegativeTermDivision)