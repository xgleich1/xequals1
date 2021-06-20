package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.SubtractionFromAnythingIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.EntireSideToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.EntireSideToAnythingMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.EntireSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term


object EntireSideToAnythingEqualsRelationStep : ShiftingStep<Term, Term>(
        EntireSideToAnythingMatcher,
        EntireSideToAnythingMapper,
        EntireSideWithdrawal,
        SubtractionFromAnythingIntegrator,
        EqualsRelationWrapper)