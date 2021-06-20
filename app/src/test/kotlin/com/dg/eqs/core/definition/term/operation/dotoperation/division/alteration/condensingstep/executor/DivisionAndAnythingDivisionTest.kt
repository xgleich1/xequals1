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


class DivisionAndAnythingDivisionTest {
    @Test
    fun `Should divide a positive division through anything positive`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = PositiveDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val denominator = positiveAnything()

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                numeratorDivisionNumerator,
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominator)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative division through anything negative`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = NegativeDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val (denominator, denominatorOpposite) =
                negativeAnythingAndOpposite()

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                numeratorDivisionNumerator,
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominatorOpposite)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a positive division through anything negative`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = PositiveDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val (denominator, denominatorOpposite) =
                negativeAnythingAndOpposite()

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                numeratorDivisionNumerator,
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominatorOpposite)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative division through anything positive`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = NegativeDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val denominator = positiveAnything()

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                numeratorDivisionNumerator,
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominator)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

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

    private fun divide(numerator: Division, denominator: Term) =
            DivisionAndAnythingDivision.execute(numerator, denominator)
}