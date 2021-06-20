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


class AnythingAndOneMultiplicationTest {
    @Test
    fun `Should multiply a single term with a single positive one`() {
        // GIVEN
        val anything = anything()
        val one = PositiveValue(1)

        val left = termsOf(anything)
        val right = termsOf(one)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply several terms with a single positive one`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val one = PositiveValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(one)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply a single term with several positive ones`() {
        // GIVEN
        val anything = anything()
        val oneA = PositiveValue(1)
        val oneB = PositiveValue(1)

        val left = termsOf(anything)
        val right = termsOf(oneA, oneB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply several terms with several positive ones`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val oneA = PositiveValue(1)
        val oneB = PositiveValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(oneA, oneB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply a single term with a single negative one`() {
        // GIVEN
        val (anything, anythingOpposite) = anythingAndOpposite()
        val one = NegativeValue(1)

        val left = termsOf(anything)
        val right = termsOf(one)

        // THEN
        val expectedResult = termsOf(anythingOpposite)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a single negative one`() {
        // GIVEN
        val anythingA = anything()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val one = NegativeValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(one)

        // THEN
        val expectedResult = termsOf(anythingA, anythingOppositeB)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with an even amount of negative ones`() {
        // GIVEN
        val anything = anything()
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)

        val left = termsOf(anything)
        val right = termsOf(oneA, oneB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply several terms with an even amount of negative ones`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(oneA, oneB)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply a single term with an odd amount of negative ones`() {
        // GIVEN
        val (anything, anythingOpposite) = anythingAndOpposite()
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)
        val oneC = NegativeValue(1)

        val left = termsOf(anything)
        val right = termsOf(oneA, oneB, oneC)

        // THEN
        val expectedResult = termsOf(anythingOpposite)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with an odd amount of negative ones`() {
        // GIVEN
        val anythingA = anything()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val oneA = NegativeValue(1)
        val oneB = NegativeValue(1)
        val oneC = NegativeValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(oneA, oneB, oneC)

        // THEN
        val expectedResult = termsOf(anythingA, anythingOppositeB)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a mix of positive and negative ones with an even amount of the latter`() {
        // GIVEN
        val anything = anything()
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = NegativeValue(1)

        val left = termsOf(anything)
        val right = termsOf(oneA, oneB, oneC)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply several terms with a mix of positive and negative ones with an even amount of the latter`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = NegativeValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(oneA, oneB, oneC)

        // THEN
        assertThat(multiply(left, right)).isEqualTo(left)
    }

    @Test
    fun `Should multiply a single term with a mix of positive and negative ones with an odd amount of the latter`() {
        // GIVEN
        val (anything, anythingOpposite) = anythingAndOpposite()
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = PositiveValue(1)

        val left = termsOf(anything)
        val right = termsOf(oneA, oneB, oneC)

        // THEN
        val expectedResult = termsOf(anythingOpposite)

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a mix of positive and negative ones with an odd amount of the latter`() {
        // GIVEN
        val anythingA = anything()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val oneA = NegativeValue(1)
        val oneB = PositiveValue(1)
        val oneC = PositiveValue(1)

        val left = termsOf(anythingA, anythingB)
        val right = termsOf(oneA, oneB, oneC)

        // THEN
        val expectedResult = termsOf(anythingA, anythingOppositeB)

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
            AnythingAndOneMultiplication.execute(left, right)
}