package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.OperationDraft
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ContainerDraftTest {
    @Test
    fun `A container draft has no padding on its sides`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val container = container(inner)

        whenever(inner.firstX).thenReturn(10)
        whenever(inner.finalX).thenReturn(50)

        // WHEN
        container.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        assertThat(container.firstX).isEqualTo(10)
        assertThat(container.finalX).isEqualTo(50)
        assertThat(container.width).isEqualTo(40)
    }

    @Test
    fun `A container draft does not adjust the width of its part`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val container = container(inner)

        whenever(inner.width).thenReturn(100)

        // WHEN
        container.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(inner).width = 100
    }

    @Test
    fun `A container draft does not adjust the height of its part`() {
        // GIVEN
        val inner: OperationDraft = mock { on { origin } doReturn mock() }
        val container = container(inner)

        whenever(inner.height).thenReturn(40)

        // WHEN
        container.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(inner).height = 40
    }

    private fun container(inner: OperationDraft) = TestAnyContainerDraft(inner)

    private class TestAnyContainerDraft(inner: OperationDraft) : ContainerDraft<Operation>(inner)
}