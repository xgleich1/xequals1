package com.dg.eqs.core.visualization.composition.vertical.operation.division

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.visualization.symbolization.line.sign.DivisionSign
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class RawDivisionDraftTest {
    @Test
    fun `A raw division draft has a padding on each side to make it easily distinguishable`() {
        // GIVEN
        val numerator: TermDraft = mock { on { firstX } doReturn 10; on { finalX } doReturn 50 }
        val denominator: TermDraft = mock { on { firstX } doReturn 10; on { finalX } doReturn 50 }
        val sign: DivisionSign = mock { on { firstX } doReturn 10; on { finalX } doReturn 50 }
        val division = division(numerator = numerator, denominator = denominator, sign = sign)

        // WHEN
        division.draft(pencil(1, 1, 1, 1, 1, 1, 1, 5, 6))

        // THEN
        assertThat(division.firstX).isEqualTo(10 - 5)
        assertThat(division.finalX).isEqualTo(50 + 6)
        assertThat(division.width).isEqualTo(40 + 5 + 6)
    }

    @Test
    fun `A raw division draft does not adjust the width of its operands`() {
        // GIVEN
        val numerator: TermDraft = mock { on { width } doReturn 40 }
        val denominator: TermDraft = mock { on { width } doReturn 80 }
        val division = division(numerator = numerator, denominator = denominator)

        // WHEN
        division.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(numerator).width = 40
        verify(denominator).width = 80
    }

    @Test
    fun `A raw division draft adjusts the width of its sign to match its widest operand`() {
        // GIVEN
        val numerator: TermDraft = mock { on { width } doReturn 40 }
        val denominator: TermDraft = mock { on { width } doReturn 80 }
        val sign: DivisionSign = mock()
        val division = division(numerator = numerator, denominator = denominator, sign = sign)

        // WHEN
        division.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(sign).width = 80
    }

    @Test
    fun `A raw division draft does not adjust the height of its parts`() {
        // GIVEN
        val numerator: TermDraft = mock { on { height } doReturn 40 }
        val denominator: TermDraft = mock { on { height } doReturn 80 }
        val sign: DivisionSign = mock { on { height } doReturn 5 }
        val division = division(numerator = numerator, denominator = denominator, sign = sign)

        // WHEN
        division.draft(pencil(1, 1, 1, 1, 1, 1, 1, 1, 1))

        // THEN
        verify(numerator).height = 40
        verify(denominator).height = 80
        verify(sign).height = 5
    }

    private fun division(origin: Division = mock(),
                         numerator: TermDraft = mock(),
                         denominator: TermDraft = mock(),
                         sign: DivisionSign = mock()): RawDivisionDraft {

        return RawDivisionDraft(origin, numerator, denominator, sign)
    }
}