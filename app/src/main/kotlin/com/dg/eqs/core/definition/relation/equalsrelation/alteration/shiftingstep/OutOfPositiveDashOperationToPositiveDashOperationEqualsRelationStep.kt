package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.SubtractionFromPositiveDashOperationIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfPositiveDashOperationToPositiveDashOperationMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfPositiveDashOperationToPositiveDashOperationMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object OutOfPositiveDashOperationToPositiveDashOperationEqualsRelationStep
    : ShiftingStep<PositiveDashOperation, PositiveDashOperation>(
        OutOfPositiveDashOperationToPositiveDashOperationMatcher,
        OutOfPositiveDashOperationToPositiveDashOperationMapper,
        OutOfSideWithdrawal,
        SubtractionFromPositiveDashOperationIntegrator,
        EqualsRelationWrapper)