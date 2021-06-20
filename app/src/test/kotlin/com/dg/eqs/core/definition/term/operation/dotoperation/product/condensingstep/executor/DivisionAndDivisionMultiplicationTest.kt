package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndDivisionMultiplicationTest {
    @Test
    fun `Should multiply a positive division with a positive division`() {
        // GIVEN
        val leftNumerator = anything()
        val leftDenominator = anything()
        val left = PositiveDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val rightDenominator = anything()
        val right = PositiveDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        leftNumerator,
                        rightNumerator),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative division with a negative division`() {
        // GIVEN
        val leftNumerator = anything()
        val leftDenominator = anything()
        val left = NegativeDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val rightDenominator = anything()
        val right = NegativeDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        leftNumerator,
                        rightNumerator),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive division with a negative division`() {
        // GIVEN
        val leftNumerator = anything()
        val leftDenominator = anything()
        val left = PositiveDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val rightDenominator = anything()
        val right = NegativeDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        leftNumerator,
                        rightNumerator),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative division with a positive division`() {
        // GIVEN
        val leftNumerator = anything()
        val leftDenominator = anything()
        val left = NegativeDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val rightDenominator = anything()
        val right = PositiveDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        leftNumerator,
                        rightNumerator),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun multiply(left: Division, right: Division) =
            DivisionAndDivisionMultiplication.execute(left, right)
}