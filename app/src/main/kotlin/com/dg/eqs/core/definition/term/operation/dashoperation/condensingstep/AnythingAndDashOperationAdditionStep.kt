package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.AnythingAndDashOperationExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.AnythingAndDashOperationMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.AnythingAndDashOperationMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.AnythingAndDashOperationAddition


object AnythingAndDashOperationAdditionStep : CondensingStep<Terms, DashOperation>(
        AnythingAndDashOperationMatcher,
        AnythingAndDashOperationExtractor,
        AnythingAndDashOperationMapper,
        AnythingAndDashOperationAddition)