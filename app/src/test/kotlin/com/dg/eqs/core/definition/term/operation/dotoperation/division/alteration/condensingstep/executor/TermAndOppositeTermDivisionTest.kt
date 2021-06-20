package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndOppositeTermDivisionTest {
    @Test
    fun `Should divide a term through its opposite`() {
        // GIVEN
        val (numerator, denominator) = termAndOpposite()

        // THEN
        val expectedResult = termsOf(NegativeValue(1))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun termAndOpposite(): Pair<Term, Term> {
        val anything: Term = mock()
        val opposite: Term = mock()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun divide(numerator: Term, denominator: Term) =
            TermAndOppositeTermDivision.execute(numerator, denominator)
}