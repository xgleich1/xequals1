package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.AdditionToZeroIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfNegativeDashOperationToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfNegativeDashOperationToZeroMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation


object OutOfNegativeDashOperationToZeroEqualsRelationStep
    : ShiftingStep<NegativeDashOperation, Term>(
        OutOfNegativeDashOperationToZeroMatcher,
        OutOfNegativeDashOperationToAnythingMapper,
        OutOfSideWithdrawal,
        AdditionToZeroIntegrator,
        EqualsRelationWrapper)