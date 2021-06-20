package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndDivisionExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.TermAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndDivisionMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.AnythingAndDivisionDivision


object AnythingAndDivisionDivisionStep : CondensingStep<Term, Division>(
        AnythingAndDivisionMatcher,
        AnythingAndDivisionExtractor,
        TermAndDivisionMapper,
        AnythingAndDivisionDivision)