package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.text.sign.ClosingSign
import com.dg.eqs.core.visualization.symbolization.text.sign.OpeningSign


class BracketDraft<out T : Operation>(
        inner: Draft<T>,
        prefix: OpeningSign = OpeningSign(inner.origin),
        suffix: ClosingSign = ClosingSign(inner.origin))
    : HorizontalEnvelope<T>(inner, prefix, suffix) {

    override fun specifyPaddingLeft(pencil: Pencil) = pencil.bracketDraftPaddingLeft

    override fun specifyPaddingRight(pencil: Pencil) = pencil.bracketDraftPaddingRight

    override fun calculatePartWidth(part: AnyDraft) = part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height
}