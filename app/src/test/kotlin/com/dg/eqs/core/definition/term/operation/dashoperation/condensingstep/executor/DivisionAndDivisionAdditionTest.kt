package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndDivisionAdditionTest {
    @Test
    fun `Should add a positive division to a positive division`() {
        // GIVEN
        val leftNumerator = anything()
        val (leftDenominator, leftDenominatorCopy) = anythingAndCopy()
        val left = PositiveDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val (rightDenominator, rightDenominatorCopy) = anythingAndCopy()
        val right = PositiveDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                leftNumerator,
                                rightDenominatorCopy),
                        PositiveProduct(
                                leftDenominatorCopy,
                                rightNumerator)),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to a negative division`() {
        // GIVEN
        val (leftNumerator, leftNumeratorOpposite) = anythingAndOpposite()
        val (leftDenominator, leftDenominatorCopy) = anythingAndCopy()
        val left = NegativeDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val (rightDenominator, rightDenominatorCopy) = anythingAndCopy()
        val right = NegativeDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                leftNumeratorOpposite,
                                rightDenominatorCopy),
                        NegativeProduct(
                                leftDenominatorCopy,
                                rightNumerator)),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive division to a negative division`() {
        // GIVEN
        val leftNumerator = anything()
        val (leftDenominator, leftDenominatorCopy) = anythingAndCopy()
        val left = PositiveDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val (rightDenominator, rightDenominatorCopy) = anythingAndCopy()
        val right = NegativeDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                leftNumerator,
                                rightDenominatorCopy),
                        NegativeProduct(
                                leftDenominatorCopy,
                                rightNumerator)),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to a positive division`() {
        // GIVEN
        val (leftNumerator, leftNumeratorOpposite) = anythingAndOpposite()
        val (leftDenominator, leftDenominatorCopy) = anythingAndCopy()
        val left = NegativeDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val (rightDenominator, rightDenominatorCopy) = anythingAndCopy()
        val right = PositiveDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                leftNumeratorOpposite,
                                rightDenominatorCopy),
                        PositiveProduct(
                                leftDenominatorCopy,
                                rightNumerator)),
                PositiveProduct(
                        leftDenominator,
                        rightDenominator)))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndCopy(): Pair<Term, Term> {
        val anything = anything()
        val anythingCopy = anything()

        whenever(anything.copy())
                .thenReturn(anythingCopy)

        return anything and anythingCopy
    }

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun add(left: Division, right: Division) =
            DivisionAndDivisionAddition.execute(left, right)
}