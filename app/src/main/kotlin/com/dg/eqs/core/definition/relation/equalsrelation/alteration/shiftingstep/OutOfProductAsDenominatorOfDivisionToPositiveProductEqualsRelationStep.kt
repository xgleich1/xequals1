package com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingStep
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator.MultiplicationToPositiveProductIntegrator
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper.OutOfDivisionToPositiveProductMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher.OutOfProductAsDenominatorOfDivisionToPositiveProductMatcher
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.withdrawal.OutOfSideWithdrawal
import com.dg.eqs.core.definition.relation.equalsrelation.alteration.shiftingstep.wrapper.EqualsRelationWrapper
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object OutOfProductAsDenominatorOfDivisionToPositiveProductEqualsRelationStep
    : ShiftingStep<Division, PositiveProduct>(
        OutOfProductAsDenominatorOfDivisionToPositiveProductMatcher,
        OutOfDivisionToPositiveProductMapper,
        OutOfSideWithdrawal,
        MultiplicationToPositiveProductIntegrator,
        EqualsRelationWrapper)