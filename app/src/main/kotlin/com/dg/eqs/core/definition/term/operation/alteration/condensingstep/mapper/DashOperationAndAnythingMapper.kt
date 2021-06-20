package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.extension.typedSingle
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation


object DashOperationAndAnythingMapper : CondensingMapper<DashOperation, Terms> {
    override fun map(left: Terms, right: Terms) = left.typedSingle<DashOperation>() and right
}