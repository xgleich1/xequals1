package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyTextSign
import com.dg.eqs.base.abbreviation.OperationDraft
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class HorizontalEnvelopeTest {
    @Test
    fun `A horizontal envelopes origin is the origin of its inner part`() {
        // GIVEN
        val originOfInner: Operation = mock()
        val inner: OperationDraft = mock { on { origin } doReturn originOfInner }
        val envelope = envelope(inner = inner)

        // THEN
        assertThat(envelope.origin).isEqualTo(originOfInner)
    }

    @Test
    fun `A horizontal envelopes prefix inner and suffix parts are ordered in this sequence`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val prefix: AnyTextSign = mock()
        val suffix: AnyTextSign = mock()
        val envelope = envelope(inner, prefix, suffix)

        // THEN
        assertThat(envelope.parts).isEqualTo(draftsOf(prefix, inner, suffix))
    }

    @Test
    fun `A horizontal envelopes baseline is the baseline of its inner part`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val envelope = envelope(inner = inner)

        whenever(inner.baseline).thenReturn(100)

        // THEN
        assertThat(envelope.baseline).isEqualTo(100)
    }

    private fun envelope(inner: OperationDraft = mock(), prefix: AnyTextSign? = null, suffix: AnyTextSign? = null) =
            TestAnyHorizontalEnvelope(inner, prefix, suffix)

    private class TestAnyHorizontalEnvelope(inner: OperationDraft, prefix: AnyTextSign?, suffix: AnyTextSign?)
        : HorizontalEnvelope<Operation>(inner, prefix, suffix) {

        override fun specifyPaddingLeft(pencil: Pencil) = TODO("not implemented")

        override fun specifyPaddingRight(pencil: Pencil) = TODO("not implemented")

        override fun calculatePartWidth(part: AnyDraft) = TODO("not implemented")

        override fun calculatePartHeight(part: AnyDraft) = TODO("not implemented")
    }
}