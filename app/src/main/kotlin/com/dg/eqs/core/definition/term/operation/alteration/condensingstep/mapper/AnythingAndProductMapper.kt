package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.extension.typedSingle
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingMapper
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product


object AnythingAndProductMapper : CondensingMapper<Terms, Product> {
    override fun map(left: Terms, right: Terms) = left and right.typedSingle<Product>()
}