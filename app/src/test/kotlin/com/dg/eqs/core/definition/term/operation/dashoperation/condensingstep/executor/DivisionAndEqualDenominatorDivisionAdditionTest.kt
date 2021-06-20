package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndEqualDenominatorDivisionAdditionTest {
    @Test
    fun `Should add a positive division to a positive division with an equal denominator`() {
        // GIVEN
        val leftNumerator = anything()
        val leftDenominator = anything()
        val left = PositiveDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val rightDenominator = anything()
        val right = PositiveDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        leftNumerator,
                        rightNumerator),
                leftDenominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to a negative division with an equal denominator`() {
        // GIVEN
        val (leftNumerator, leftNumeratorOpposite) = anythingAndOpposite()
        val leftDenominator = anything()
        val left = NegativeDivision(leftNumerator, leftDenominator)

        val (rightNumerator, rightNumeratorOpposite) = anythingAndOpposite()
        val rightDenominator = anything()
        val right = NegativeDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        leftNumeratorOpposite,
                        rightNumeratorOpposite),
                leftDenominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive division to a negative division with an equal denominator`() {
        // GIVEN
        val leftNumerator = anything()
        val leftDenominator = anything()
        val left = PositiveDivision(leftNumerator, leftDenominator)

        val (rightNumerator, rightNumeratorOpposite) = anythingAndOpposite()
        val rightDenominator = anything()
        val right = NegativeDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        leftNumerator,
                        rightNumeratorOpposite),
                leftDenominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to a positive division with an equal denominator`() {
        // GIVEN
        val (leftNumerator, leftNumeratorOpposite) = anythingAndOpposite()
        val leftDenominator = anything()
        val left = NegativeDivision(leftNumerator, leftDenominator)

        val rightNumerator = anything()
        val rightDenominator = anything()
        val right = PositiveDivision(rightNumerator, rightDenominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        leftNumeratorOpposite,
                        rightNumerator),
                leftDenominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun add(left: Division, right: Division) =
            DivisionAndEqualDenominatorDivisionAddition.execute(left, right)
}