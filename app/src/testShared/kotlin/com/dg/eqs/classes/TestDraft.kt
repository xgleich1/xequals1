package com.dg.eqs.classes

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Pencil
import org.mockito.kotlin.mock


fun draft(
        origin: AnyOrigin = mock(),
        width: Int = 0,
        height: Int = 0,
        firstX: Int = 0,
        firstY: Int = 0,
        baseline: Int = 0): AnyDraft =
        TestDraft(origin, baseline).also {
            it.width = width
            it.height = height
            it.firstX = firstX
            it.firstY = firstY
        }

private class TestDraft(origin: AnyOrigin, override val baseline: Int) : Draft<AnyOrigin>(origin) {
    override fun draft(pencil: Pencil) = TODO("not implemented")

    override fun moveX(amount: Int) = TODO("not implemented")

    override fun moveY(amount: Int) = TODO("not implemented")

    override fun scale(factor: Float) = TODO("not implemented")

    override fun unravel() = TODO("not implemented")

    override fun touch(touch: Touch): TermDraft = TODO("not implemented")

    override fun contains(draft: AnyDraft) = TODO("not implemented")
}