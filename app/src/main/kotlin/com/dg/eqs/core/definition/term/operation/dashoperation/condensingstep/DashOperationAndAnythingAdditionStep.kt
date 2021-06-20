package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingStep
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor.DashOperationAndAnythingExtractor
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper.DashOperationAndAnythingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher.DashOperationAndAnythingMatcher
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor.DashOperationAndAnythingAddition


object DashOperationAndAnythingAdditionStep : CondensingStep<DashOperation, Terms>(
        DashOperationAndAnythingMatcher,
        DashOperationAndAnythingExtractor,
        DashOperationAndAnythingMapper,
        DashOperationAndAnythingAddition)