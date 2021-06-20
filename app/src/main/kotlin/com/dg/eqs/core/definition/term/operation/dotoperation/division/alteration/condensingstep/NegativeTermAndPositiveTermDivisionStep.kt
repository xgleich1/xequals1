package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndTermMapper
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.NegativeTermAndPositiveTermDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.extractor.TermAndTermFromDivisionExtractor
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher.NegativeTermAndPositiveTermInDivisionMatcher


object NegativeTermAndPositiveTermDivisionStep : CondensingStep<Term, Term>(
        NegativeTermAndPositiveTermInDivisionMatcher,
        TermAndTermFromDivisionExtractor,
        TermAndTermMapper,
        NegativeTermAndPositiveTermDivision)