package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndDivisionDivisionTest {
    @Test
    fun `Should divide a positive division through a positive division`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = PositiveDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = PositiveDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        numeratorDivisionNumerator,
                        denominatorDivisionDenominator),
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominatorDivisionNumerator)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative division through a negative division`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = NegativeDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = NegativeDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        numeratorDivisionNumerator,
                        denominatorDivisionDenominator),
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominatorDivisionNumerator)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a positive division through a negative division`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = PositiveDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = NegativeDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        numeratorDivisionNumerator,
                        denominatorDivisionDenominator),
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominatorDivisionNumerator)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative division through a positive division`() {
        // GIVEN
        val numeratorDivisionNumerator = anything()
        val numeratorDivisionDenominator = anything()
        val numerator = NegativeDivision(
                numeratorDivisionNumerator,
                numeratorDivisionDenominator)

        val denominatorDivisionNumerator = anything()
        val denominatorDivisionDenominator = anything()
        val denominator = PositiveDivision(
                denominatorDivisionNumerator,
                denominatorDivisionDenominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        numeratorDivisionNumerator,
                        denominatorDivisionDenominator),
                PositiveProduct(
                        numeratorDivisionDenominator,
                        denominatorDivisionNumerator)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun divide(numerator: Division, denominator: Division) =
            DivisionAndDivisionDivision.execute(numerator, denominator)
}