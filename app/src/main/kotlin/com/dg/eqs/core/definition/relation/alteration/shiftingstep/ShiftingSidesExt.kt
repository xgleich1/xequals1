package com.dg.eqs.core.definition.relation.alteration.shiftingstep

import com.dg.eqs.core.definition.term.Term


infix fun <S : Term, T : Term> S.and(targetSide: T) = ShiftingSides(this, targetSide)