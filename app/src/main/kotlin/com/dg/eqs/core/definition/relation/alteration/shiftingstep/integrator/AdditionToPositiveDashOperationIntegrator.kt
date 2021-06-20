package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingIntegrator
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object AdditionToPositiveDashOperationIntegrator : ShiftingIntegrator<PositiveDashOperation> {
    override fun integrate(source: Terms, targetSide: PositiveDashOperation) =
            targetSide.addToBack(source)
}