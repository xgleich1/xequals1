package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDivisionAdditionTest {
    @Test
    fun `Should add a single term to a positive division`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val numerator = anything()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val right = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                anything,
                                denominatorCopy),
                        numerator),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add several terms to a positive division`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val numerator = anything()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val right = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveDashOperation(
                                        anythingA,
                                        anythingB),
                                denominatorCopy),
                        numerator),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a single term to a negative division`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val right = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                anything,
                                denominatorCopy),
                        numeratorOpposite),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add several terms to a negative division`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val right = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveDashOperation(
                                        anythingA,
                                        anythingB),
                                denominatorCopy),
                        numeratorOpposite),
                denominator))

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

    private fun add(left: Terms, right: Division) =
            AnythingAndDivisionAddition.execute(left, right)
}