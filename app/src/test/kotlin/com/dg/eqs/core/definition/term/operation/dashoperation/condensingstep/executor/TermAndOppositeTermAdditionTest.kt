package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndOppositeTermAdditionTest {
    @Test
    fun `Should add a term to its opposite`() {
        // GIVEN
        val (left, right) = termAndOpposite()

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun termAndOpposite(): Pair<Term, Term> {
        val anything: Term = mock()
        val opposite: Term = mock()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun add(left: Term, right: Term) =
            TermAndOppositeTermAddition.execute(left, right)
}