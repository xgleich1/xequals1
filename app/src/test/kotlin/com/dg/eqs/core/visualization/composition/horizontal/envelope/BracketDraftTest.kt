package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.OperationDraft
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.symbolization.text.sign.ClosingSign
import com.dg.eqs.core.visualization.symbolization.text.sign.OpeningSign
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class BracketDraftTest {
    @Test
    fun `A bracket drafts prefix is a opening sign`() {
        // GIVEN
        val originOfInner: Operation = mock()
        val inner: OperationDraft = mock { on { origin } doReturn originOfInner }
        val bracket = bracket(inner)

        // THEN
        assertThat(bracket.prefix).isEqualTo(OpeningSign(originOfInner))
    }

    @Test
    fun `A bracket drafts suffix is a closing sign`() {
        // GIVEN
        val originOfInner: Operation = mock()
        val inner: OperationDraft = mock { on { origin } doReturn originOfInner }
        val bracket = bracket(inner)

        // THEN
        assertThat(bracket.suffix).isEqualTo(ClosingSign(originOfInner))
    }

    @Test
    fun `A bracket draft has a padding on each side to make it easily distinguishable`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: OpeningSign = mock()
        val suffix: ClosingSign = mock()
        val bracket = bracket(inner, prefix, suffix)

        whenever(inner.firstX).thenReturn(20)
        whenever(inner.finalX).thenReturn(40)

        whenever(prefix.firstX).thenReturn(10)
        whenever(prefix.finalX).thenReturn(20)

        whenever(suffix.firstX).thenReturn(40)
        whenever(suffix.finalX).thenReturn(50)

        // WHEN
        bracket.draft(pencil(1, 1, 1, 1, 5, 6, 1, 1, 1))

        // THEN
        assertThat(bracket.firstX).isEqualTo(10 - 5)
        assertThat(bracket.finalX).isEqualTo(50 + 6)
        assertThat(bracket.width).isEqualTo(40 + 5 + 6)
    }

    @Test
    fun `A bracket draft does not adjust the width of its parts`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: OpeningSign = mock()
        val suffix: ClosingSign = mock()
        val bracket = bracket(inner, prefix, suffix)

        whenever(inner.width).thenReturn(40)
        whenever(prefix.width).thenReturn(30)
        whenever(suffix.width).thenReturn(20)

        // WHEN
        bracket.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(inner).width = 40
        verify(prefix).width = 30
        verify(suffix).width = 20
    }

    @Test
    fun `A bracket draft does not adjust the height of its parts`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: OpeningSign = mock()
        val suffix: ClosingSign = mock()
        val bracket = bracket(inner, prefix, suffix)

        whenever(inner.height).thenReturn(40)
        whenever(prefix.height).thenReturn(30)
        whenever(suffix.height).thenReturn(20)

        // WHEN
        bracket.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(inner).height = 40
        verify(prefix).height = 30
        verify(suffix).height = 20
    }

    private fun bracket(inner: OperationDraft = mock()) = BracketDraft(inner)

    private fun bracket(inner: OperationDraft = mock(), prefix: OpeningSign = mock(), suffix: ClosingSign = mock()) =
            BracketDraft(inner, prefix, suffix)
}