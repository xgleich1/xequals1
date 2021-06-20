package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndDivisionMapper
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.AnythingAndDivisionThroughPositiveOneAddition
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor.AnythingAndDivisionThroughPositiveOneExtractor
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher.AnythingAndDivisionThroughPositiveOneMatcher
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object AnythingAndDivisionThroughPositiveOneAdditionStep : CondensingStep<Terms, Division>(
        AnythingAndDivisionThroughPositiveOneMatcher,
        AnythingAndDivisionThroughPositiveOneExtractor,
        AnythingAndDivisionMapper,
        AnythingAndDivisionThroughPositiveOneAddition)