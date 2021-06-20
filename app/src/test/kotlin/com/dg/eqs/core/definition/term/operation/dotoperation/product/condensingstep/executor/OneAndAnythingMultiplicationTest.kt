package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class OneAndAnythingMultiplicationTest {
    @Test
    fun `Should multiply a single positive one with a single term`() {
        // GIVEN
        val one = PositiveValue(1)
        val anything = anything()

        val left = termsOf(one)
        val right = termsOf(anything)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply a single positive one with several terms`() {
        // GIVEN
        val one = PositiveValue(1)
        val anythingA = anything()
        val anythingB = anything()

        val left = termsOf(one)
        val right = termsOf(anythingA, anythingB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply several positive ones with a single term`() {
        // GIVEN
        val oneA = PositiveValue(1)
        val oneB = PositiveValue(1)
        val anything = anything()

        val left = termsOf(oneA, oneB)
        val right = termsOf(anything)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply several positive ones with several terms`() {
        // GIVEN
        val oneA = PositiveValue(1)
        val oneB = PositiveValue(1)
        val anythingA = anything()
        val anythingB = anything()

        val left = termsOf(oneA, oneB)
        val right = termsOf(anythingA, anythingB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply a single negative one with a single term`() {
        // GIVEN
        val one = NegativeValue(1)
        val (anything, anythingOpposite) = anythingAndOpposite()

        val left = termsOf(one)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(anythingOpposite)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single negative one with several terms`() {
        // GIVEN
        val one = NegativeValue(1)
        val (anythingA, anythingOppositeA) = anythingAndOpposite()
        val anythingB = anything()

        val left = termsOf(one)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(anythingOppositeA, anythingB)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply an even amount of negative ones with a single term`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)
        val anything = anything()

        val left = termsOf(oneA, oneB)
        val right = termsOf(anything)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply an even amount of negative ones with several terms`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)
        val anythingA = anything()
        val anythingB = anything()

        val left = termsOf(oneA, oneB)
        val right = termsOf(anythingA, anythingB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply an odd amount of negative ones with a single term`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)
        val oneC = NegativeValue(1)
        val (anything, anythingOpposite) = anythingAndOpposite()

        val left = termsOf(oneA, oneB, oneC)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(anythingOpposite)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply an odd amount of negative ones with several terms`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)
        val oneC = NegativeValue(1)
        val (anythingA, anythingOppositeA) = anythingAndOpposite()
        val anythingB = anything()

        val left = termsOf(oneA, oneB, oneC)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(anythingOppositeA, anythingB)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a mix of positive and negative ones with an even amount of the latter with a single term`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = NegativeValue(1)
        val anything = anything()

        val left = termsOf(oneA, oneB, oneC)
        val right = termsOf(anything)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply a mix of positive and negative ones with an even amount of the latter with several terms`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = NegativeValue(1)
        val anythingA = anything()
        val anythingB = anything()

        val left = termsOf(oneA, oneB, oneC)
        val right = termsOf(anythingA, anythingB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(right)
    }

    @Test
    fun `Should multiply a mix of positive and negative ones with an odd amount of the latter with a single term`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = PositiveValue(1)
        val (anything, anythingOpposite) = anythingAndOpposite()

        val left = termsOf(oneA, oneB, oneC)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(anythingOpposite)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a mix of positive and negative ones with an odd amount of the latter with several terms`() {
        // GIVEN
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = PositiveValue(1)
        val (anythingA, anythingOppositeA) = anythingAndOpposite()
        val anythingB = anything()

        val left = termsOf(oneA, oneB, oneC)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(anythingOppositeA, anythingB)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun multiply(left: Terms, right: Terms) =
            OneAndAnythingMultiplication.execute(left, right)
}