package com.dg.eqs.core.visualization.composition.vertical

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


class VerticalCompositeTest {
    @Test
    fun `A vertical composites first part is its anchor and thus not moved horizontally`() {
        // GIVEN
        val firstPart: AnyDraft = mock()
        val composite = composite(parts = draftsOf(firstPart))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(firstPart, never()).moveX(anyInt())
    }

    @Test
    fun `A vertical composites first part is its anchor and thus not moved vertically`() {
        // GIVEN
        val firstPart: AnyDraft = mock()
        val composite = composite(parts = draftsOf(firstPart))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(firstPart, never()).moveY(anyInt())
    }

    @Test
    fun `Should align each part of a vertical composite to the horizontal center of its predecessor`() {
        // GIVEN
        val partA: AnyDraft = mock { on { centerX } doReturn 70 }
        val partB: AnyDraft = mock { on { centerX } doReturn 60 }
        val partC: AnyDraft = mock { on { centerX } doReturn 40 }
        val partD: AnyDraft = mock { on { centerX } doReturn 10 }
        val composite = composite(parts = draftsOf(partA, partB, partC, partD))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(partB).moveX(10)
        verify(partC).moveX(20)
        verify(partD).moveX(30)
    }

    @Test
    fun `Should position each part of a vertical composite below its predecessor`() {
        // GIVEN
        val partA: AnyDraft = mock { on { firstY } doReturn 0; on { finalY } doReturn 20 }
        val partB: AnyDraft = mock { on { firstY } doReturn 0; on { finalY } doReturn 30 }
        val partC: AnyDraft = mock { on { firstY } doReturn 0; on { finalY } doReturn 40 }
        val partD: AnyDraft = mock { on { firstY } doReturn 0; on { finalY } doReturn 50 }
        val composite = composite(parts = draftsOf(partA, partB, partC, partD))

        // WHEN
        composite.draft(pencil())

        // THEN
        verify(partB).moveY(20)
        verify(partC).moveY(30)
        verify(partD).moveY(40)
    }

    private fun composite(origin: AnyOrigin = mock(), parts: AnyDrafts) = TestAnyVerticalComposite(origin, parts)

    private class TestAnyVerticalComposite(origin: AnyOrigin, parts: AnyDrafts) : VerticalComposite<AnyOrigin>(origin, parts) {
        override val baseline = 0


        override fun specifyPaddingLeft(pencil: Pencil) = 0

        override fun specifyPaddingRight(pencil: Pencil) = 0

        override fun calculatePartWidth(part: AnyDraft) = 0

        override fun calculatePartHeight(part: AnyDraft) = 0
    }
}