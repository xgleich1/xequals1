package com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object OutOfDivisionToAnythingMapper : ShiftingMapper<Division, Term> {
    override fun map(sourceSide: Term, targetSide: Term) =
            sourceSide as Division and targetSide
}