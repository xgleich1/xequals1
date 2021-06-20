package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDivisionDivisionTest {
    @Test
    fun `Should divide anything positive through a positive division`() {
        // GIVEN
        val numerator = positiveAnything()

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = PositiveDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        numerator,
                        denominatorDivisionDenominator),
                denominatorDivisionNumerator))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide anything negative through a negative division`() {
        // GIVEN
        val (numerator, numeratorOpposite) =
                negativeAnythingAndOpposite()

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = NegativeDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        numeratorOpposite,
                        denominatorDivisionDenominator),
                denominatorDivisionNumerator))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide anything negative through a positive division`() {
        // GIVEN
        val (numerator, numeratorOpposite) =
                negativeAnythingAndOpposite()

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = PositiveDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        numeratorOpposite,
                        denominatorDivisionDenominator),
                denominatorDivisionNumerator))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide anything positive through a negative division`() {
        // GIVEN
        val numerator = positiveAnything()

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = NegativeDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        numerator,
                        denominatorDivisionDenominator),
                denominatorDivisionNumerator))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun positiveAnything() = anything().apply {
        whenever(isPositive).thenReturn(true)
    }

    private fun negativeAnything() = anything().apply {
        whenever(isPositive).thenReturn(false)
    }

    private fun negativeAnythingAndOpposite(): Pair<Term, Term> {
        val anything = negativeAnything()
        val opposite = positiveAnything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun anything(): Term = mock()

    private fun divide(numerator: Term, denominator: Division) =
            AnythingAndDivisionDivision.execute(numerator, denominator)
}