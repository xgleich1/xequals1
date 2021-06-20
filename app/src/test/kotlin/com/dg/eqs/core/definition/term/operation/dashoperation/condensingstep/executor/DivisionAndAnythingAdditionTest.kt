package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
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


class DivisionAndAnythingAdditionTest {
    @Test
    fun `Should add a positive division to a positive term`() {
        // GIVEN
        val numerator = anything()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = PositiveDivision(numerator, denominator)

        val anything = positiveAnything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numerator,
                        PositiveProduct(
                                denominatorCopy,
                                anything)),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive division to several terms starting with a positive one`() {
        // GIVEN
        val numerator = anything()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = PositiveDivision(numerator, denominator)

        val anythingA = positiveAnything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numerator,
                        PositiveProduct(
                                denominatorCopy,
                                PositiveDashOperation(
                                        anythingA,
                                        anythingB))),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to a negative term`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = NegativeDivision(numerator, denominator)

        val (anything, anythingOpposite) = negativeAnythingAndOpposite()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numeratorOpposite,
                        NegativeProduct(
                                denominatorCopy,
                                anythingOpposite)),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to several terms starting with a negative one`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = NegativeDivision(numerator, denominator)

        val (anythingA, anythingOppositeA) = negativeAnythingAndOpposite()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numeratorOpposite,
                        NegativeProduct(
                                denominatorCopy,
                                PositiveDashOperation(
                                        anythingOppositeA,
                                        anythingOppositeB))),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive division to a negative term`() {
        // GIVEN
        val numerator = anything()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = PositiveDivision(numerator, denominator)

        val (anything, anythingOpposite) = negativeAnythingAndOpposite()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numerator,
                        NegativeProduct(
                                denominatorCopy,
                                anythingOpposite)),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive division to several terms starting with a negative one`() {
        // GIVEN
        val numerator = anything()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = PositiveDivision(numerator, denominator)

        val (anythingA, anythingOppositeA) = negativeAnythingAndOpposite()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numerator,
                        NegativeProduct(
                                denominatorCopy,
                                PositiveDashOperation(
                                        anythingOppositeA,
                                        anythingOppositeB))),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to a positive term`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = NegativeDivision(numerator, denominator)

        val anything = positiveAnything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numeratorOpposite,
                        PositiveProduct(
                                denominatorCopy,
                                anything)),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division to several terms starting with a positive one`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val (denominator, denominatorCopy) = anythingAndCopy()
        val left = NegativeDivision(numerator, denominator)

        val anythingA = positiveAnything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numeratorOpposite,
                        PositiveProduct(
                                denominatorCopy,
                                PositiveDashOperation(
                                        anythingA,
                                        anythingB))),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun positiveAnything() = anything().apply {
        whenever(isPositive).thenReturn(true)
    }

    private fun negativeAnything() = anything().apply {
        whenever(isPositive).thenReturn(false)
    }

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

    private fun negativeAnythingAndOpposite(): Pair<Term, Term> {
        val anything = negativeAnything()
        val opposite = positiveAnything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun add(left: Division, right: Terms) =
            DivisionAndAnythingAddition.execute(left, right)
}