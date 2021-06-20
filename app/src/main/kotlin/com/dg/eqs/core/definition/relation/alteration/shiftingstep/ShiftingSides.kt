package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term


data class ShiftingSides<S : Term, T : Term>(val sourceSide: S, val targetSide: T)