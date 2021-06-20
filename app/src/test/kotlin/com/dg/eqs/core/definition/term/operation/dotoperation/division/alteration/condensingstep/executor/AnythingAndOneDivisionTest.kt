package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndOneDivisionTest {
    @Test
    fun `Should divide anything through a positive one`() {
        // GIVEN
        val numerator = anything()
        val denominator = PositiveValue(1)

        // THEN
        val expectedResult = termsOf(numerator)

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide anything through a negative one`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val denominator = NegativeValue(1)

        // THEN
        val expectedResult = termsOf(numeratorOpposite)

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun divide(numerator: Term, denominator: Value) =
            AnythingAndOneDivision.execute(numerator, denominator)
}