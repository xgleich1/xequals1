package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DashOperationAndDashOperationExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DashOperationAndDashOperationMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DashOperationAndDashOperationMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor.DashOperationAndDashOperationMultiplication


object DashOperationAndDashOperationMultiplicationStep : CondensingStep<DashOperation, DashOperation>(
        DashOperationAndDashOperationMatcher,
        DashOperationAndDashOperationExtractor,
        DashOperationAndDashOperationMapper,
        DashOperationAndDashOperationMultiplication)