package com.dg.eqs.core.visualization

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.classes.draft
import com.dg.eqs.core.interaction.Touch
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DraftTest {
    @Test
    fun `A draft provides its halved width`() {
        // GIVEN
        val draft = draft(width = 100)

        // THEN
        assertThat(draft.halfWidth).isEqualTo(100 / 2)
    }

    @Test
    fun `A draft provides its halved height`() {
        // GIVEN
        val draft = draft(height = 100)

        // THEN
        assertThat(draft.halfHeight).isEqualTo(100 / 2)
    }

    @Test
    fun `A draft provides its center x-coordinate`() {
        // GIVEN
        val draft = draft(width = 100, firstX = 25)

        // THEN
        assertThat(draft.centerX).isEqualTo(25 + 100 / 2)
    }

    @Test
    fun `A draft provides its center y-coordinate`() {
        // GIVEN
        val draft = draft(height = 100, firstY = 25)

        // THEN
        assertThat(draft.centerY).isEqualTo(25 + 100 / 2)
    }

    @Test
    fun `A draft provides its final x-coordinate`() {
        // GIVEN
        val draft = draft(width = 100, firstX = 25)

        // THEN
        assertThat(draft.finalX).isEqualTo(25 + 100)
    }

    @Test
    fun `A draft provides its final y-coordinate`() {
        // GIVEN
        val draft = draft(height = 100, firstY = 25)

        // THEN
        assertThat(draft.finalY).isEqualTo(25 + 100)
    }

    @Test
    fun `Should compare two equal drafts`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val draftA = draft(origin, 150, 125, 75, 50, 25)
        val draftB = draft(origin, 150, 125, 75, 50, 25)

        // THEN
        assertThat(draftA).isEqualTo(draftB)
    }

    @Test
    fun `A draft is touched when touching it returns a result`() {
        // GIVEN
        val draft = spy(draft())
        val touch: Touch = mock()
        val result: TermDraft = mock()

        doReturn(result).whenever(draft).touch(touch)

        // THEN
        assertTrue(draft.isTouched(touch))
    }

    @Test
    fun `A draft isn't touched when touching it returns nothing`() {
        // GIVEN
        val draft = spy(draft())
        val touch: Touch = mock()

        doReturn(null).whenever(draft).touch(touch)

        // THEN
        assertFalse(draft.isTouched(touch))
    }
}