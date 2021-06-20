package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DivisionAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.DivisionAndEqualDenominatorDivisionAddition
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor.DivisionAndEqualDenominatorDivisionExtractor
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher.DivisionAndEqualDenominatorDivisionMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionAndEqualDenominatorDivisionAdditionStep : CondensingStep<Division, Division>(
        DivisionAndEqualDenominatorDivisionMatcher,
        DivisionAndEqualDenominatorDivisionExtractor,
        DivisionAndDivisionMapper,
        DivisionAndEqualDenominatorDivisionAddition)