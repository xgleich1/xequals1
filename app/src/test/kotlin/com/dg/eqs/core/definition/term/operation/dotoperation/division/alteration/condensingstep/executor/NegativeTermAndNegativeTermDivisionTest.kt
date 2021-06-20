package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class NegativeTermAndNegativeTermDivisionTest {
    @Test
    fun `Should divide a negative term through a negative term`() {
        // GIVEN
        val (numerator, numeratorOpposite) =
                negativeTermAndOpposite()

        val (denominator, denominatorOpposite) =
                negativeTermAndOpposite()

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                numeratorOpposite,
                denominatorOpposite))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun negativeTermAndOpposite(): Pair<Term, Term> {
        val anything: Term = mock { on { isNegative } doReturn true }
        val opposite: Term = mock { on { isPositive } doReturn true }

        whenever(anything.invert()).thenReturn(opposite)

        return anything and opposite
    }

    private fun divide(numerator: Term, denominator: Term) =
            NegativeTermAndNegativeTermDivision.execute(numerator, denominator)
}