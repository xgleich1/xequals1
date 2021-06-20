package com.dg.eqs.core.definition.relation.alteration.shiftingstep.integrator

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingIntegrator
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct


object MultiplicationToAnythingIntegrator : ShiftingIntegrator<Term> {
    override fun integrate(source: Terms, targetSide: Term) =
            PositiveProduct(termsOf(targetSide, source))
}