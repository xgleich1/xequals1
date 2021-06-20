package com.dg.eqs.core.visualization.composition.vertical.operation.division

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.composition.vertical.operation.VerticalOperation
import com.dg.eqs.core.visualization.symbolization.line.sign.DivisionSign
import kotlin.math.max


class RawDivisionDraft(
        origin: Division,
        numerator: TermDraft,
        denominator: TermDraft,
        sign: DivisionSign = DivisionSign(origin))
    : VerticalOperation<Division>(origin, numerator, denominator, sign) {

    override fun specifyPaddingLeft(pencil: Pencil) = pencil.divisionDraftPaddingLeft

    override fun specifyPaddingRight(pencil: Pencil) = pencil.divisionDraftPaddingRight

    override fun calculatePartWidth(part: AnyDraft) = if(part === sign) max(top.width, bottom.width) else part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height
}