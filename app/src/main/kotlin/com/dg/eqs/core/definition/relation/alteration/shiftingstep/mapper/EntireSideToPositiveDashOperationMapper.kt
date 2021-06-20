package com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object EntireSideToPositiveDashOperationMapper
    : ShiftingMapper<Term, PositiveDashOperation> {

    override fun map(sourceSide: Term, targetSide: Term) =
            sourceSide and targetSide as PositiveDashOperation
}