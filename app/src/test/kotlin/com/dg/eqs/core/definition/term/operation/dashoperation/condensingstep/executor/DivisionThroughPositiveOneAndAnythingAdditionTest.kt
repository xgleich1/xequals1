package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionThroughPositiveOneAndAnythingAdditionTest {
    @Test
    fun `Should add a positive division through positive one to a single term`() {
        // GIVEN
        val numerator = anything()
        val denominator = PositiveValue(1)
        val left = PositiveDivision(numerator, denominator)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numerator,
                        anything),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive division through positive one to several terms`() {
        // GIVEN
        val numerator = anything()
        val denominator = PositiveValue(1)
        val left = PositiveDivision(numerator, denominator)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numerator,
                        anythingA,
                        anythingB),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division through positive one to a single term`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val denominator = PositiveValue(1)
        val left = NegativeDivision(numerator, denominator)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numeratorOpposite,
                        anything),
                denominator))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative division through positive one to several terms`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val denominator = PositiveValue(1)
        val left = NegativeDivision(numerator, denominator)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        numeratorOpposite,
                        anythingA,
                        anythingB),
                denominator))

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

    private fun add(left: Division, right: Terms) =
            DivisionThroughPositiveOneAndAnythingAddition.execute(left, right)
}