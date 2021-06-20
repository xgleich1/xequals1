package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and


object AnythingAndAnythingMapper : CondensingMapper<Terms, Terms> {
    override fun map(left: Terms, right: Terms) = left and right
}