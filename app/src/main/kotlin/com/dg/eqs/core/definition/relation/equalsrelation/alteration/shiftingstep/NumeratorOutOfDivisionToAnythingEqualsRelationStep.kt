package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.DivisionFromAnythingIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfDivisionToAnythingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.NumeratorOutOfDivisionToAnythingMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object NumeratorOutOfDivisionToAnythingEqualsRelationStep : ShiftingStep<Division, Term>(
        NumeratorOutOfDivisionToAnythingMatcher,
        OutOfDivisionToAnythingMapper,
        OutOfSideWithdrawal,
        DivisionFromAnythingIntegrator,
        EqualsRelationWrapper)