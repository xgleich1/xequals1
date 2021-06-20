package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.AdditionToPositiveDashOperationIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfNegativeDashOperationToPositiveDashOperationMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfNegativeDashOperationToPositiveDashOperationMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object OutOfNegativeDashOperationToPositiveDashOperationEqualsRelationStep
    : ShiftingStep<NegativeDashOperation, PositiveDashOperation>(
        OutOfNegativeDashOperationToPositiveDashOperationMatcher,
        OutOfNegativeDashOperationToPositiveDashOperationMapper,
        OutOfSideWithdrawal,
        AdditionToPositiveDashOperationIntegrator,
        EqualsRelationWrapper)