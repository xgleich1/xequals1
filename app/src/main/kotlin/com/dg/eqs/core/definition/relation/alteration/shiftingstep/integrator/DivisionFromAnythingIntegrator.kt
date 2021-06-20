package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingIntegrator
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object DivisionFromAnythingIntegrator : ShiftingIntegrator<Term> {
    override fun integrate(source: Terms, targetSide: Term) =
            PositiveDivision(targetSide, source.toSingleOrProduct())

    private fun Terms.toSingleOrProduct() = singleOrNull() ?: PositiveProduct(this)
}