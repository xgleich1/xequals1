package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingIntegrator
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object AdditionToZeroIntegrator : ShiftingIntegrator<Term> {
    override fun integrate(source: Terms, targetSide: Term) =
            source.toSingleOrDashOperation()

    private fun Terms.toSingleOrDashOperation() =
            singleOrNull() ?: PositiveDashOperation(this)
}