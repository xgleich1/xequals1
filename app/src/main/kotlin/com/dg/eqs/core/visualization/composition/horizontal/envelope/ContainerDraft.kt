package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Pencil


abstract class ContainerDraft<out T : Operation>(inner: Draft<T>)
    : HorizontalEnvelope<T>(inner) {

    override fun specifyPaddingLeft(pencil: Pencil) = 0

    override fun specifyPaddingRight(pencil: Pencil) = 0

    override fun calculatePartWidth(part: AnyDraft) = part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height
}