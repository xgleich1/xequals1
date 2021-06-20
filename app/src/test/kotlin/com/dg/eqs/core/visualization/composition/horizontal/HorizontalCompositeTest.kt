package com.dg.eqs.core.visualization.composition.horizontal

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyDrafts
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt


class HorizontalCompositeTest {
    @Test
    fun `A horizontal composites first part is its anchor and thus not moved horizontally`() {
        // GIVEN
        val firstPart: AnyDraft = mock()
        val composite = composite(parts = draftsOf(firstPart))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(firstPart, never()).moveX(anyInt())
    }

    @Test
    fun `A horizontal composites first part is its anchor and thus not moved vertically`() {
        // GIVEN
        val firstPart: AnyDraft = mock()
        val composite = composite(parts = draftsOf(firstPart))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(firstPart, never()).moveY(anyInt())
    }

    @Test
    fun `Should position each part of a horizontal composite right next to its predecessor`() {
        // GIVEN
        val partA: AnyDraft = mock { on { firstX } doReturn 0; on { finalX } doReturn 20 }
        val partB: AnyDraft = mock { on { firstX } doReturn 0; on { finalX } doReturn 30 }
        val partC: AnyDraft = mock { on { firstX } doReturn 0; on { finalX } doReturn 40 }
        val partD: AnyDraft = mock { on { firstX } doReturn 0; on { finalX } doReturn 50 }
        val composite = composite(parts = draftsOf(partA, partB, partC, partD))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(partB).moveX(20)
        verify(partC).moveX(30)
        verify(partD).moveX(40)
    }

    @Test
    fun `Should align each part of a horizontal composite to the baseline of its predecessor`() {
        // GIVEN
        val partA: AnyDraft = mock { on { baseline } doReturn 70 }
        val partB: AnyDraft = mock { on { baseline } doReturn 60 }
        val partC: AnyDraft = mock { on { baseline } doReturn 40 }
        val partD: AnyDraft = mock { on { baseline } doReturn 10 }
        val composite = composite(parts = draftsOf(partA, partB, partC, partD))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(partB).moveY(10)
        verify(partC).moveY(20)
        verify(partD).moveY(30)
    }

    private fun composite(origin: AnyOrigin = mock(), parts: AnyDrafts) = TestAnyHorizontalComposite(origin, parts)

    private class TestAnyHorizontalComposite(origin: AnyOrigin, parts: AnyDrafts) : HorizontalComposite<AnyOrigin>(origin, parts) {
        override val baseline = 0


        override fun specifyPaddingLeft(pencil: Pencil) = 0

        override fun specifyPaddingRight(pencil: Pencil) = 0

        override fun calculatePartWidth(part: AnyDraft) = 0

        override fun calculatePartHeight(part: AnyDraft) = 0
    }
}