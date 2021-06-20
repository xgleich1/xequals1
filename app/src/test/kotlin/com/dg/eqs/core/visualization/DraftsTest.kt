package com.dg.eqs.core.visualization

import com.dg.eqs.base.abbreviation.*
import com.dg.eqs.core.interaction.Touch
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNull
import org.junit.Test


class DraftsTest {
    @Test
    fun `A drafts decorator provides the origins of its content`() {
        // GIVEN
        val originA: AnyOrigin = mock()
        val originB: AnyOrigin = mock()
        val draftA: AnyDraft = mock { on { origin } doReturn originA }
        val draftB: AnyDraft = mock { on { origin } doReturn originB }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        val expectedOrigins = listOf(originA, originB)

        assertThat(drafts.origins).isEqualTo(expectedOrigins)
    }

    @Test
    fun `A drafts decorator provides the combined width of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { firstX } doReturn 25; on { finalX } doReturn 100 }
        val draftB: AnyDraft = mock { on { firstX } doReturn 100; on { finalX } doReturn 175 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.width).isEqualTo(175 - 25)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the combined width from`() {
        draftsOf<AnyOrigin>().width
    }

    @Test
    fun `A drafts decorator provides the combined height of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { firstY } doReturn 25; on { finalY } doReturn 100 }
        val draftB: AnyDraft = mock { on { firstY } doReturn 100; on { finalY } doReturn 175 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.height).isEqualTo(175 - 25)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the combined height from`() {
        draftsOf<AnyOrigin>().height
    }

    @Test
    fun `A drafts decorator provides the leftmost x coordinate of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { firstX } doReturn 25 }
        val draftB: AnyDraft = mock { on { firstX } doReturn 50 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.firstX).isEqualTo(25)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the leftmost x coordinate from`() {
        draftsOf<AnyOrigin>().firstX
    }

    @Test
    fun `A drafts decorator provides the topmost y coordinate of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { firstY } doReturn 25 }
        val draftB: AnyDraft = mock { on { firstY } doReturn 50 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.firstY).isEqualTo(25)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the topmost y coordinate from`() {
        draftsOf<AnyOrigin>().firstY
    }

    @Test
    fun `A drafts decorator provides the horizontal center of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { firstX } doReturn 25; on { finalX } doReturn 100 }
        val draftB: AnyDraft = mock { on { firstX } doReturn 100; on { finalX } doReturn 175 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.centerX).isEqualTo(100)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the horizontal center from`() {
        draftsOf<AnyOrigin>().centerX
    }

    @Test
    fun `A drafts decorator provides the vertical center of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { firstY } doReturn 25; on { finalY } doReturn 100 }
        val draftB: AnyDraft = mock { on { firstY } doReturn 100; on { finalY } doReturn 175 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.centerY).isEqualTo(100)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the vertical center from`() {
        draftsOf<AnyOrigin>().centerY
    }

    @Test
    fun `A drafts decorator provides the rightmost x coordinate of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { finalX } doReturn 25 }
        val draftB: AnyDraft = mock { on { finalX } doReturn 50 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.finalX).isEqualTo(50)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the rightmost x coordinate from`() {
        draftsOf<AnyOrigin>().finalX
    }

    @Test
    fun `A drafts decorator provides the bottommost y coordinate of its content`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { finalY } doReturn 25 }
        val draftB: AnyDraft = mock { on { finalY } doReturn 50 }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.finalY).isEqualTo(50)
    }

    @Test(expected = IllegalStateException::class)
    fun `A drafts decorator throws an exception when there is no content to get the bottommost y coordinate from`() {
        draftsOf<AnyOrigin>().finalY
    }

    @Test
    fun `Should compare two equal drafts decorator`() {
        // GIVEN
        val draft: AnyDraft = mock()
        val draftsA = draftsOf(draft)
        val draftsB = draftsOf(draft)

        // THEN
        assertThat(draftsA).isEqualTo(draftsB)
    }

    @Test
    fun `Should convert a drafts decorator to a string`() {
        // GIVEN
        val draftA: AnyDraft = mock { on { toString() } doReturn "A" }
        val draftB: AnyDraft = mock { on { toString() } doReturn "B" }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts).hasToString("[A, B]")
    }

    @Test
    fun `A drafts decorator is able to draft all of its drafts with the provided pencil`() {
        // GIVEN
        val pencil: Pencil = mock()
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val drafts = draftsOf(draftA, draftB)

        // WHEN
        drafts.draft(pencil)

        // THEN
        verify(draftA).draft(pencil)
        verify(draftB).draft(pencil)
    }

    @Test
    fun `A drafts decorator is able to horizontally move all of its drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val drafts = draftsOf(draftA, draftB)

        // WHEN
        drafts.moveX(100)

        // THEN
        verify(draftA).moveX(100)
        verify(draftB).moveX(100)
    }

    @Test
    fun `A drafts decorator is able to vertically move all of its drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val drafts = draftsOf(draftA, draftB)

        // WHEN
        drafts.moveY(100)

        // THEN
        verify(draftA).moveY(100)
        verify(draftB).moveY(100)
    }

    @Test
    fun `A drafts decorator is able to scale all of its drafts`() {
        // GIVEN
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val drafts = draftsOf(draftA, draftB)

        // WHEN
        drafts.scale(0.5f)

        // THEN
        verify(draftA).scale(0.5f)
        verify(draftB).scale(0.5f)
    }

    @Test
    fun `A drafts decorator is able to unravel all of its drafts`() {
        // GIVEN
        val symbolA: AnySymbolDraft = mock()
        val symbolB: AnySymbolDraft = mock()
        val symbolC: AnySymbolDraft = mock()
        val symbolD: AnySymbolDraft = mock()
        val draftA: AnyDraft = mock { on { unravel() } doReturn symbolsOf(symbolA, symbolB) }
        val draftB: AnyDraft = mock { on { unravel() } doReturn symbolsOf(symbolC, symbolD) }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.unravel()).isEqualTo(symbolsOf(symbolA, symbolB, symbolC, symbolD))
    }

    @Test
    fun `A drafts decorator is able to touch all of its drafts`() {
        // GIVEN
        val touch: Touch = mock()
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val drafts = draftsOf(draftA, draftB)

        // WHEN
        drafts.touch(touch)

        // THEN
        verify(draftA).touch(touch)
        verify(draftB).touch(touch)
    }

    @Test
    fun `A drafts decorator is able to find the first touched draft`() {
        // GIVEN
        val touch: Touch = mock()
        val draftA: TermDraft = mock { on { touch(touch) } doReturn it }
        val draftB: TermDraft = mock { on { touch(touch) } doReturn it }
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertThat(drafts.touch(touch)).isEqualTo(draftA)
    }

    @Test
    fun `A drafts decorator returns null when none of its drafts is touched`() {
        // GIVEN
        val touch: Touch = mock()
        val draftA: AnyDraft = mock()
        val draftB: AnyDraft = mock()
        val drafts = draftsOf(draftA, draftB)

        // THEN
        assertNull(drafts.touch(touch))
    }
}