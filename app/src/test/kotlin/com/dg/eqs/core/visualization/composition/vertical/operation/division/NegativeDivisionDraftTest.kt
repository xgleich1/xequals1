package com.dg.eqs.core.visualization.composition.vertical.operation.division

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.visualization.composition.horizontal.envelope.NegationDraft
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class NegativeDivisionDraftTest {
    @Test
    fun `A negative division draft is the negative representation of a division`() {
        // GIVEN
        val division: Division = mock()
        val numerator: TermDraft = mock()
        val denominator: TermDraft = mock()
        val divisionDraft = NegativeDivisionDraft(division, numerator, denominator)

        // THEN
        val expectedInner = NegationDraft(RawDivisionDraft(division, numerator, denominator))

        assertThat(divisionDraft.inner).isEqualTo(expectedInner)
    }

    @Test
    fun `A negative division draft is the choosable parent of its operands`() {
        // GIVEN
        val division: Division = mock()
        val numerator: TermDraft = mock()
        val denominator: TermDraft = mock()
        val divisionDraft = NegativeDivisionDraft(division, numerator, denominator)

        // THEN
        verify(numerator).choosableParent = divisionDraft
        verify(denominator).choosableParent = divisionDraft
    }
}