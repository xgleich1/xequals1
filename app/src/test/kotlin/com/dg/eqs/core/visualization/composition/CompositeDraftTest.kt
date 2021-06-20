package com.dg.eqs.core.visualization.composition

import com.dg.eqs.base.abbreviation.*
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.INITIAL
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.text.item.value.PositiveValueDraft
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test


class CompositeDraftTest {
    private companion object {
        private const val PADDING_LEFT = 10
        private const val PADDING_RIGHT = 20
        private const val PART_WIDTH_ADJUST = 30
        private const val PART_HEIGHT_ADJUST = 40
    }

    @Test
    fun `Should compare two equal composite drafts`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val partA: AnyDraft = mock()
        val partB: AnyDraft = mock()
        val compositeA = composite(origin, draftsOf(partA, partB))
        val compositeB = composite(origin, draftsOf(partA, partB))

        // THEN
        assertThat(compositeA).isEqualTo(compositeB)
    }

    @Test
    fun `Should convert a composite draft to a string`() {
        // GIVEN
        val partA: AnyDraft = mock { on { toString() } doReturn "A" }
        val partB: AnyDraft = mock { on { toString() } doReturn "B" }
        val composite = composite(parts = draftsOf(partA, partB))

        // THEN
        assertThat(composite).hasToString("[A, B]")
    }

    @Test
    fun `Should draft a composite draft and all of its parts`() {
        // GIVEN
        val partA = PositiveValueDraft(mock())
        val partB = PositiveValueDraft(mock())
        val composite = composite(parts = draftsOf(partA, partB))

        // WHEN
        composite.draft(pencil(1, 1, 50, 1, 1, 1, 1, 1, 1))

        // THEN
        assertThat(composite.width).isEqualTo(190)
        assertThat(composite.height).isEqualTo(180)
        assertThat(composite.firstX).isEqualTo(-10)
        assertThat(composite.firstY).isEqualTo(0)

        assertThat(partA.width).isEqualTo(80)
        assertThat(partA.height).isEqualTo(90)
        assertThat(partA.firstX).isEqualTo(0)
        assertThat(partA.firstY).isEqualTo(0)

        assertThat(partB.width).isEqualTo(80)
        assertThat(partB.height).isEqualTo(90)
        assertThat(partB.firstX).isEqualTo(80)
        assertThat(partB.firstY).isEqualTo(90)
    }

    @Test
    fun `Should horizontally move a composite draft and all of its parts`() {
        // GIVEN
        val partA: AnyDraft = mock()
        val partB: AnyDraft = mock()
        val composite = composite(parts = draftsOf(partA, partB), firstX = 50)

        // WHEN
        composite.moveX(100)

        // THEN
        assertThat(composite.firstX).isEqualTo(150)

        verify(partA).moveX(100)
        verify(partB).moveX(100)
    }

    @Test
    fun `Should vertically move a composite draft and all of its parts`() {
        // GIVEN
        val partA: AnyDraft = mock()
        val partB: AnyDraft = mock()
        val composite = composite(parts = draftsOf(partA, partB), firstY = 50)

        // WHEN
        composite.moveY(100)

        // THEN
        assertThat(composite.firstY).isEqualTo(150)

        verify(partA).moveY(100)
        verify(partB).moveY(100)
    }

    @Test
    fun `Should scale a composite draft and all of its parts`() {
        // GIVEN
        val partA = PositiveValueDraft(mock())
        val partB = PositiveValueDraft(mock())
        val composite = composite(parts = draftsOf(partA, partB))

        composite.draft(pencil(1, 1, 50, 1, 1, 1, 1, 1, 1))

        // WHEN
        composite.scale(0.5f)

        // THEN
        assertThat(composite.width).isEqualTo(125)
        assertThat(composite.height).isEqualTo(130)
        assertThat(composite.firstX).isEqualTo(-5)
        assertThat(composite.firstY).isEqualTo(0)

        assertThat(partA.width).isEqualTo(55)
        assertThat(partA.height).isEqualTo(65)
        assertThat(partA.firstX).isEqualTo(0)
        assertThat(partA.firstY).isEqualTo(0)

        assertThat(partB.width).isEqualTo(55)
        assertThat(partB.height).isEqualTo(65)
        assertThat(partB.firstX).isEqualTo(55)
        assertThat(partB.firstY).isEqualTo(65)
    }

    @Test
    fun `A composite draft unravels into the symbols of its parts`() {
        // GIVEN
        val partA: AnySymbolDraft = mock { on { unravel() } doReturn symbolsOf(it) }
        val partB: AnySymbolDraft = mock { on { unravel() } doReturn symbolsOf(it) }
        val composite = composite(parts = draftsOf(partA, partB))

        // THEN
        assertThat(composite.unravel()).isEqualTo(symbolsOf(partA, partB))
    }

    @Test
    fun `A composite draft returns its touched part`() {
        // GIVEN
        val partA: TermDraft = mock { on { touch(touch(100, 50)) } doReturn it }
        val partB: TermDraft = mock { on { touch(touch(125, 75)) } doReturn it }
        val composite = composite(parts = draftsOf(partA, partB))

        // WHEN
        val touched = composite.touch(touch(125, 75))

        // THEN
        assertThat(touched).isEqualTo(partB)
    }

    @Test
    fun `A composite draft returns null when no part of it is touched`() {
        // GIVEN
        val partA: TermDraft = mock { on { touch(touch(100, 50)) } doReturn it }
        val partB: TermDraft = mock { on { touch(touch(125, 75)) } doReturn it }
        val composite = composite(parts = draftsOf(partA, partB))

        // WHEN
        val touched = composite.touch(touch(200, 100))

        // THEN
        assertNull(touched)
    }

    @Test
    fun `A composite draft involves itself`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val partA: AnyDraft = mock()
        val partB: AnyDraft = mock()
        val compositeA = composite(origin, draftsOf(partA, partB))
        val compositeB = composite(origin, draftsOf(partA, partB))

        // THEN
        assertTrue(compositeA in compositeA)
        assertTrue(compositeB !in compositeA)
    }

    @Test
    fun `A composite draft involves its parts and their parts`() {
        // GIVEN
        val partA: AnyDraft = mock { on { contains(it) } doReturn true }
        val partB: AnyDraft = mock { on { contains(it) } doReturn true }
        val partC: AnyDraft = mock { on { contains(it) } doReturn true }
        val partD: AnyDraft = mock { on { contains(it) } doReturn true }
        val partE = composite(parts = draftsOf(partC, mock<AnyDraft>()))
        val partF = composite(parts = draftsOf(mock<AnyDraft>(), partD))
        val composite = composite(parts = draftsOf(partE, partF))

        // THEN
        assertTrue(partA !in composite)
        assertTrue(partB !in composite)
        assertTrue(partC in composite)
        assertTrue(partD in composite)
        assertTrue(partE in composite)
        assertTrue(partF in composite)
    }

    private fun touch(x: Int, y: Int) = Touch(x, y, INITIAL)

    private fun composite(origin: AnyOrigin = mock(), parts: AnyDrafts, firstX: Int = 0, firstY: Int = 0) =
            TestAnyCompositeDraft(origin, parts).also { it.firstX = firstX; it.firstY = firstY }

    private class TestAnyCompositeDraft(origin: AnyOrigin, parts: AnyDrafts) : CompositeDraft<AnyOrigin>(origin, parts) {
        override val baseline = 0


        override fun specifyPaddingLeft(pencil: Pencil) = PADDING_LEFT

        override fun specifyPaddingRight(pencil: Pencil) = PADDING_RIGHT

        override fun calculatePartWidth(part: AnyDraft) = part.width + PART_WIDTH_ADJUST

        override fun calculatePartHeight(part: AnyDraft) = part.height + PART_HEIGHT_ADJUST

        override fun calculatePartShiftX(base: AnyDraft, part: AnyDraft) = base.finalX - part.firstX

        override fun calculatePartShiftY(base: AnyDraft, part: AnyDraft) = base.finalY - part.firstY
    }
}