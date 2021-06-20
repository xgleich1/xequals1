package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingIntegrator
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object MultiplicationToPositiveProductIntegrator : ShiftingIntegrator<PositiveProduct> {
    override fun integrate(source: Terms, targetSide: PositiveProduct) =
            targetSide.multiplyToBack(source)
}