package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DivisionAndDivisionExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DivisionAndDivisionMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.DivisionAndDivisionAddition
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionAndDivisionAdditionStep : CondensingStep<Division, Division>(
        DivisionAndDivisionMatcher,
        DivisionAndDivisionExtractor,
        DivisionAndDivisionMapper,
        DivisionAndDivisionAddition)