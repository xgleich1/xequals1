package com.dg.eqs.core.definition.relation.alteration.shiftingstep.mapper

import com.dg.eqs.core.definition.relation.alteration.shiftingstep.ShiftingMapper
import com.dg.eqs.core.definition.relation.alteration.shiftingstep.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object OutOfProductToAnythingMapper : ShiftingMapper<Product, Term> {
    override fun map(sourceSide: Term, targetSide: Term) =
            sourceSide as Product and targetSide
}