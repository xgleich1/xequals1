package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class PositiveTermAndNegativeTermDivisionTest {
    @Test
    fun `Should divide a positive term through a negative term`() {
        // GIVEN
        val numerator = positiveTerm()

        val (denominator, denominatorOpposite) =
                negativeTermAndOpposite()

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                numerator,
                denominatorOpposite))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun positiveTerm(): Term = mock { on { isPositive } doReturn true }

    private fun negativeTerm(): Term = mock { on { isNegative } doReturn true }

    private fun negativeTermAndOpposite(): Pair<Term, Term> {
        val anything = negativeTerm()
        val opposite = positiveTerm()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun divide(numerator: Term, denominator: Term) =
            PositiveTermAndNegativeTermDivision.execute(numerator, denominator)
}