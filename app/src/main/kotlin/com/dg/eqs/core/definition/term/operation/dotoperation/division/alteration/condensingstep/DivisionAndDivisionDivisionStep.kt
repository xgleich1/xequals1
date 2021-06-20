package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DivisionAndDivisionExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DivisionAndDivisionMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor.DivisionAndDivisionDivision


object DivisionAndDivisionDivisionStep : CondensingStep<Division, Division>(
        DivisionAndDivisionMatcher,
        DivisionAndDivisionExtractor,
        DivisionAndDivisionMapper,
        DivisionAndDivisionDivision)