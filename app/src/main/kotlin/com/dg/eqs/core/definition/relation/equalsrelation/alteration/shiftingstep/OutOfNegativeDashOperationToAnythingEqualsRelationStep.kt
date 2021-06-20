package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.AdditionToAnythingIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfNegativeDashOperationToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfNegativeDashOperationToAnythingMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation


object OutOfNegativeDashOperationToAnythingEqualsRelationStep
    : ShiftingStep<NegativeDashOperation, Term>(
        OutOfNegativeDashOperationToAnythingMatcher,
        OutOfNegativeDashOperationToAnythingMapper,
        OutOfSideWithdrawal,
        AdditionToAnythingIntegrator,
        EqualsRelationWrapper)