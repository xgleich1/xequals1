package com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.classes.font
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.pencil
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.visualization.symbolization.text.sign.MinusSign
import com.dg.eqs.core.visualization.symbolization.text.sign.PlusSign
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RawDashOperationDraftTest {
    @Test
    fun `A raw dash operation draft creates its signs depending on its operands`() {
        // GIVEN
        val dashOperation: DashOperation = mock()
        val operandA: TermDraft = mock {
            on { origin } doReturn positiveTerm()
        }
        val operandB: TermDraft = mock {
            on { origin } doReturn negativeTerm()
        }
        val operandC: TermDraft = mock {
            on { origin } doReturn positiveTerm()
        }
        val dashOperationDraft = dashOperationDraft(
                dashOperation,
                draftsOf(operandA, operandB, operandC))

        // THEN
        assertThat(dashOperationDraft.signs).isEqualTo(listOf(
                MinusSign(dashOperation),
                PlusSign(dashOperation)))
    }

    @Test
    fun `A raw dash operation draft has no padding on its sides`() {
        // GIVEN
        val operandA: TermDraft = mock {
            on { origin } doReturn positiveTerm()
            on { firstX } doReturn 10
            on { finalX } doReturn 20
        }
        val operandB: TermDraft = mock {
            on { origin } doReturn negativeTerm()
            on { firstX } doReturn 30
            on { finalX } doReturn 40
        }
        val operandC: TermDraft = mock {
            on { origin } doReturn positiveTerm()
            on { firstX } doReturn 50
            on { finalX } doReturn 60
        }
        val dashOperationDraft = dashOperationDraft(
                operands = draftsOf(operandA, operandB, operandC))

        // WHEN
        dashOperationDraft.draft(pencil(
                font(textSize = Size(1, 1)),
                1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        assertThat(dashOperationDraft.firstX).isEqualTo(10)
        assertThat(dashOperationDraft.finalX).isEqualTo(60)
        assertThat(dashOperationDraft.width).isEqualTo(50)
    }

    @Test
    fun `A raw dash operation draft does not adjust the width of its parts`() {
        // GIVEN
        val operandA: TermDraft = mock {
            on { origin } doReturn positiveTerm()
            on { width } doReturn 10
        }
        val operandB: TermDraft = mock {
            on { origin } doReturn negativeTerm()
            on { width } doReturn 20
        }
        val operandC: TermDraft = mock {
            on { origin } doReturn positiveTerm()
            on { width } doReturn 30
        }
        val dashOperationDraft = dashOperationDraft(
                operands = draftsOf(operandA, operandB, operandC))

        // WHEN
        dashOperationDraft.draft(pencil(
                font(textSize = Size(40, 1)),
                1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(operandA).width = 10
        verify(operandB).width = 20
        verify(operandC).width = 30

        assertThat(dashOperationDraft.signs.first.width).isEqualTo(40)
        assertThat(dashOperationDraft.signs.second.width).isEqualTo(40)
    }

    @Test
    fun `A raw dash operation draft does not adjust the height of its parts`() {
        // GIVEN
        val operandA: TermDraft = mock {
            on { origin } doReturn positiveTerm()
            on { height } doReturn 10
        }
        val operandB: TermDraft = mock {
            on { origin } doReturn negativeTerm()
            on { height } doReturn 20
        }
        val operandC: TermDraft = mock {
            on { origin } doReturn positiveTerm()
            on { height } doReturn 30
        }
        val dashOperationDraft = dashOperationDraft(
                operands = draftsOf(operandA, operandB, operandC))

        // WHEN
        dashOperationDraft.draft(pencil(
                font(textSize = Size(1, 40)),
                1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(operandA).height = 10
        verify(operandB).height = 20
        verify(operandC).height = 30

        assertThat(dashOperationDraft.signs.first.height).isEqualTo(40)
        assertThat(dashOperationDraft.signs.second.height).isEqualTo(40)
    }

    private fun dashOperationDraft(origin: DashOperation = mock(), operands: TermDrafts) =
            RawDashOperationDraft(origin, operands)
}