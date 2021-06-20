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


class NegativeTermAndPositiveTermDivisionTest {
    @Test
    fun `Should divide a negative term through a positive term`() {
        // GIVEN
        val (numerator, numeratorOpposite) =
                negativeTermAndOpposite()

        val denominator = positiveTerm()

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                numeratorOpposite,
                denominator))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun negativeTermAndOpposite(): Pair<Term, Term> {
        val anything = negativeTerm()
        val opposite = positiveTerm()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun negativeTerm(): Term = mock { on { isNegative } doReturn true }

    private fun positiveTerm(): Term = mock { on { isPositive } doReturn true }

    private fun divide(numerator: Term, denominator: Term) =
            NegativeTermAndPositiveTermDivision.execute(numerator, denominator)
}