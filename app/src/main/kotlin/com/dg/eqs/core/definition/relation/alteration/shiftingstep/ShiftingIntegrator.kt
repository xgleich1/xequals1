package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


interface ShiftingIntegrator<in T : Term> {
    fun integrate(source: Terms, targetSide: T): Term
}