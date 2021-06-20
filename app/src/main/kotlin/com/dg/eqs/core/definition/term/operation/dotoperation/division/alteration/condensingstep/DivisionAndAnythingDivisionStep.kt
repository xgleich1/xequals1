package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DivisionAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndTermMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DivisionAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.DivisionAndAnythingDivision


object DivisionAndAnythingDivisionStep : CondensingStep<Division, Term>(
        DivisionAndAnythingMatcher,
        DivisionAndAnythingExtractor,
        DivisionAndTermMapper,
        DivisionAndAnythingDivision)