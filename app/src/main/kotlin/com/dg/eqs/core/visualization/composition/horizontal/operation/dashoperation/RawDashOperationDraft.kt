package com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.composition.horizontal.operation.HorizontalOperation
import com.dg.eqs.core.visualization.symbolization.text.sign.MinusSign
import com.dg.eqs.core.visualization.symbolization.text.sign.PlusSign


class RawDashOperationDraft(origin: DashOperation, operands: TermDrafts)
    : HorizontalOperation<DashOperation>(origin, operands, operands.toSigns(origin)) {

    constructor(origin: DashOperation, vararg operands: TermDraft)
            : this(origin, draftsOf(*operands))

    override fun specifyPaddingLeft(pencil: Pencil) = 0

    override fun specifyPaddingRight(pencil: Pencil) = 0

    override fun calculatePartWidth(part: AnyDraft) = part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height

    private companion object SignsCreator {
        private fun TermDrafts.toSigns(origin: DashOperation) = drop(1).map {
            val term = it.origin

            if(term.isPositive) {
                PlusSign(origin)
            } else {
                MinusSign(origin)
            }
        }
    }
}