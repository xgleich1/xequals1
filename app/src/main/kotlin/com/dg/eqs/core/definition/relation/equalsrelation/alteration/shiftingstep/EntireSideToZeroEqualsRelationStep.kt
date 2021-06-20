package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.SubtractionFromZeroIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.EntireSideToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.EntireSideToZeroMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.EntireSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term


object EntireSideToZeroEqualsRelationStep : ShiftingStep<Term, Term>(
        EntireSideToZeroMatcher,
        EntireSideToAnythingMapper,
        EntireSideWithdrawal,
        SubtractionFromZeroIntegrator,
        EqualsRelationWrapper)