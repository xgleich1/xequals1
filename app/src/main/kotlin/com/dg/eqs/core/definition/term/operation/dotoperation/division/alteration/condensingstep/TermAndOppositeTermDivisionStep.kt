package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.TermAndOppositeTermExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndTermMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.TermAndOppositeTermMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.TermAndOppositeTermDivision


object TermAndOppositeTermDivisionStep : CondensingStep<Term, Term>(
        TermAndOppositeTermMatcher,
        TermAndOppositeTermExtractor,
        TermAndTermMapper,
        TermAndOppositeTermDivision)