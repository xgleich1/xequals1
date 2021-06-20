package com.dg.eqs.core.visualization.composition.horizontal.operation.product

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.visualization.composition.horizontal.envelope.BracketDraft
import com.dg.eqs.core.visualization.composition.horizontal.envelope.ContainerDraft
import com.dg.eqs.core.visualization.composition.horizontal.envelope.NegationDraft


class NegativeProductDraft(origin: Product, operands: TermDrafts)
    : ContainerDraft<Product>(NegationDraft(BracketDraft(RawProductDraft(origin, operands)))) {

    init {
        operands.forEach {
            it.choosableParent = this
        }
    }

    constructor(origin: Product, vararg operands: TermDraft) : this(origin, draftsOf(*operands))
}