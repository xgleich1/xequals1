package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.extension.single
import com.dg.eqs.base.extension.typedSingle
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


object DivisionAndTermMapper : CondensingMapper<Division, Term> {
    override fun map(left: Terms, right: Terms) = left.typedSingle<Division>() and right.single
}