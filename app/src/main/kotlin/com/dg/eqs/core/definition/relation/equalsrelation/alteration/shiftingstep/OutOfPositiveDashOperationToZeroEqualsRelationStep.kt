package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.SubtractionFromZeroIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfPositiveDashOperationToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfPositiveDashOperationToZeroMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object OutOfPositiveDashOperationToZeroEqualsRelationStep
    : ShiftingStep<PositiveDashOperation, Term>(
        OutOfPositiveDashOperationToZeroMatcher,
        OutOfPositiveDashOperationToAnythingMapper,
        OutOfSideWithdrawal,
        SubtractionFromZeroIntegrator,
        EqualsRelationWrapper)