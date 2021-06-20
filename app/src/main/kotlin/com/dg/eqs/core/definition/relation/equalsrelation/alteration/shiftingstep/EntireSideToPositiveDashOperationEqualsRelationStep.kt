package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.SubtractionFromPositiveDashOperationIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.EntireSideToPositiveDashOperationMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.EntireSideToPositiveDashOperationMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.EntireSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object EntireSideToPositiveDashOperationEqualsRelationStep
    : ShiftingStep<Term, PositiveDashOperation>(
        EntireSideToPositiveDashOperationMatcher,
        EntireSideToPositiveDashOperationMapper,
        EntireSideWithdrawal,
        SubtractionFromPositiveDashOperationIntegrator,
        EqualsRelationWrapper)