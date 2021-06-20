package com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PositiveDashOperationDraftTest {
    @Test
    fun `A positive dash operation draft is the positive representation of a dash operation`() {
        // GIVEN
        val dashOperation: DashOperation = mock()
        val operandA: TermDraft = mock { on { origin } doReturn dashOperation }
        val operandB: TermDraft = mock { on { origin } doReturn dashOperation }
        val dashOperationDraft = PositiveDashOperationDraft(dashOperation, operandA, operandB)

        // THEN
        val expectedInner = RawDashOperationDraft(dashOperation, operandA, operandB)

        assertThat(dashOperationDraft.inner).isEqualTo(expectedInner)
    }

    @Test
    fun `A positive dash operation draft is the choosable parent of its operands`() {
        // GIVEN
        val dashOperation: DashOperation = mock()
        val operandA: TermDraft = mock { on { origin } doReturn dashOperation }
        val operandB: TermDraft = mock { on { origin } doReturn dashOperation }
        val dashOperationDraft = PositiveDashOperationDraft(dashOperation, operandA, operandB)

        // THEN
        verify(operandA).choosableParent = dashOperationDraft
        verify(operandB).choosableParent = dashOperationDraft
    }
}