package com.dg.eqs.core.visualization.composition.horizontal.operation.product

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.composition.horizontal.operation.HorizontalOperation
import com.dg.eqs.core.visualization.symbolization.text.sign.TimesSign


class RawProductDraft(origin: Product, operands: TermDrafts)
    : HorizontalOperation<Product>(origin, operands, operands.toSigns(origin)) {

    constructor(origin: Product, vararg operands: TermDraft)
            : this(origin, draftsOf(*operands))

    override fun specifyPaddingLeft(pencil: Pencil) = 0

    override fun specifyPaddingRight(pencil: Pencil) = 0

    override fun calculatePartWidth(part: AnyDraft) = part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height

    private companion object SignsCreator {
        private fun TermDrafts.toSigns(origin: Product) = drop(1).map {
            TimesSign(origin)
        }
    }
}