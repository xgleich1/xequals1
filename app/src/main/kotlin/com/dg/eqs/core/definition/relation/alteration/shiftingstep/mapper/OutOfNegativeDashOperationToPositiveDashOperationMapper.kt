package com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation


object OutOfNegativeDashOperationToPositiveDashOperationMapper
    : ShiftingMapper<NegativeDashOperation, PositiveDashOperation> {

    override fun map(sourceSide: Term, targetSide: Term) =
            sourceSide as NegativeDashOperation and targetSide as PositiveDashOperation
}