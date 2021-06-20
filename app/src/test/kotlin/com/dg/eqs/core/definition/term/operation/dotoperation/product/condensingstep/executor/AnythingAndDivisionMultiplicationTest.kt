package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDivisionMultiplicationTest {
    @Test
    fun `Should multiply a single term with a positive division having a product as the numerator`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val numerator = product(anything(), anything())
        val denominator = anything()
        val right = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                product(
                        anything,
                        numerator.first,
                        numerator.second),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a positive division having no product as the numerator`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val numerator = anything()
        val denominator = anything()
        val right = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        anything,
                        numerator),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a positive division having a product as the numerator`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val numerator = product(anything(), anything())
        val denominator = anything()
        val right = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                product(
                        anythingA,
                        anythingB,
                        numerator.first,
                        numerator.second),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a positive division having no product as the numerator`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val numerator = anything()
        val denominator = anything()
        val right = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        anythingA,
                        anythingB,
                        numerator),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a negative division having a product as the numerator`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val numerator = product(anything(), anything())
        val denominator = anything()
        val right = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                product(
                        anything,
                        numerator.first,
                        numerator.second),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a negative division having no product as the numerator`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val numerator = anything()
        val denominator = anything()
        val right = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        anything,
                        numerator),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a negative division having a product as the numerator`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val numerator = product(anything(), anything())
        val denominator = anything()
        val right = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                product(
                        anythingA,
                        anythingB,
                        numerator.first,
                        numerator.second),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a negative division having no product as the numerator`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val numerator = anything()
        val denominator = anything()
        val right = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        anythingA,
                        anythingB,
                        numerator),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun product(vararg operands: Term) =
            TestProduct(termsOf(*operands))

    private fun multiply(left: Terms, right: Division) =
            AnythingAndDivisionMultiplication.execute(left, right)

    private class TestProduct(operands: Terms) : Product(operands) {
        override val isNegative = false


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TestProduct(newOperands)

        override fun applySign() = TODO("not implemented")
    }
}