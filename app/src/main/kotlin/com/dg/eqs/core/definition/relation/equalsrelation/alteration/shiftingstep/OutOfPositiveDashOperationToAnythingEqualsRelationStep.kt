package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.SubtractionFromAnythingIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfPositiveDashOperationToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfPositiveDashOperationToAnythingMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object OutOfPositiveDashOperationToAnythingEqualsRelationStep
    : ShiftingStep<PositiveDashOperation, Term>(
        OutOfPositiveDashOperationToAnythingMatcher,
        OutOfPositiveDashOperationToAnythingMapper,
        OutOfSideWithdrawal,
        SubtractionFromAnythingIntegrator,
        EqualsRelationWrapper)