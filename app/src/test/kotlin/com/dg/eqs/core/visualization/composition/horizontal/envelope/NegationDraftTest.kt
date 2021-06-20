package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.OperationDraft
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.symbolization.text.sign.MinusSign
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNull
import org.junit.Test


class NegationDraftTest {
    @Test
    fun `A negation drafts prefix is a minus sign`() {
        // GIVEN
        val originOfInner: Operation = mock()
        val inner: OperationDraft = mock { on { origin } doReturn originOfInner }
        val negation = negation(inner)

        // THEN
        assertThat(negation.prefix).isEqualTo(MinusSign(originOfInner))
    }

    @Test
    fun `A negation draft has no suffix`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val negation = negation(inner)

        // THEN
        assertNull(negation.suffix)
    }

    @Test
    fun `A negation draft has a padding on its left side to make it easily distinguishable`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: MinusSign = mock()
        val bracket = negation(inner, prefix)

        whenever(inner.firstX).thenReturn(20)
        whenever(inner.finalX).thenReturn(50)

        whenever(prefix.firstX).thenReturn(10)
        whenever(prefix.finalX).thenReturn(20)

        // WHEN
        bracket.draft(pencil(1, 1, 1, 1, 1, 1, 5, 1, 1))

        // THEN
        assertThat(bracket.firstX).isEqualTo(10 - 5)
        assertThat(bracket.finalX).isEqualTo(50)
        assertThat(bracket.width).isEqualTo(40 + 5)
    }

    @Test
    fun `A negation draft does not adjust the width of its parts`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: MinusSign = mock()
        val negation = negation(inner, prefix)

        whenever(inner.width).thenReturn(40)
        whenever(prefix.width).thenReturn(30)

        // WHEN
        negation.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(inner).width = 40
        verify(prefix).width = 30
    }

    @Test
    fun `A negation draft does not adjust the height of its parts`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: MinusSign = mock()
        val bracket = negation(inner, prefix)

        whenever(inner.height).thenReturn(40)
        whenever(prefix.height).thenReturn(30)

        // WHEN
        bracket.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(inner).height = 40
        verify(prefix).height = 30
    }

    private fun negation(inner: OperationDraft = mock()) = NegationDraft(inner)

    private fun negation(inner: OperationDraft = mock(), prefix: MinusSign = mock()) = NegationDraft(inner, prefix)
}