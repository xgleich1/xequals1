package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.text.sign.MinusSign


class NegationDraft<out T : Operation>(
        inner: Draft<T>,
        prefix: MinusSign = MinusSign(inner.origin))
    : HorizontalEnvelope<T>(inner, prefix) {

    override fun specifyPaddingLeft(pencil: Pencil) = pencil.negationDraftPaddingLeft

    override fun specifyPaddingRight(pencil: Pencil) = 0

    override fun calculatePartWidth(part: AnyDraft) = part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height
}