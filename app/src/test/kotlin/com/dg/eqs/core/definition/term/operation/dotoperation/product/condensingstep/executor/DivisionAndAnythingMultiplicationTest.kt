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


class DivisionAndAnythingMultiplicationTest {
    @Test
    fun `Should multiply a positive division having a product as the numerator with a single term`() {
        // GIVEN
        val numerator = product(anything(), anything())
        val denominator = anything()
        val left = PositiveDivision(numerator, denominator)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                product(
                        numerator.first,
                        numerator.second,
                        anything),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive division having no product as the numerator with a single term`() {
        // GIVEN
        val numerator = anything()
        val denominator = anything()
        val left = PositiveDivision(numerator, denominator)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        numerator,
                        anything),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive division having a product as the numerator with several terms`() {
        // GIVEN
        val numerator = product(anything(), anything())
        val denominator = anything()
        val left = PositiveDivision(numerator, denominator)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                product(
                        numerator.first,
                        numerator.second,
                        anythingA,
                        anythingB),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive division having no product as the numerator with several terms`() {
        // GIVEN
        val numerator = anything()
        val denominator = anything()
        val left = PositiveDivision(numerator, denominator)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveProduct(
                        numerator,
                        anythingA,
                        anythingB),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative division having a product as the numerator with a single term`() {
        // GIVEN
        val numerator = product(anything(), anything())
        val denominator = anything()
        val left = NegativeDivision(numerator, denominator)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                product(
                        numerator.first,
                        numerator.second,
                        anything),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative division having no product as the numerator with a single term`() {
        // GIVEN
        val numerator = anything()
        val denominator = anything()
        val left = NegativeDivision(numerator, denominator)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        numerator,
                        anything),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative division having a product as the numerator with several terms`() {
        // GIVEN
        val numerator = product(anything(), anything())
        val denominator = anything()
        val left = NegativeDivision(numerator, denominator)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                product(
                        numerator.first,
                        numerator.second,
                        anythingA,
                        anythingB),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative division having no product as the numerator with several terms`() {
        // GIVEN
        val numerator = anything()
        val denominator = anything()
        val left = NegativeDivision(numerator, denominator)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveProduct(
                        numerator,
                        anythingA,
                        anythingB),
                denominator))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun product(vararg operands: Term) =
            TestProduct(termsOf(*operands))

    private fun multiply(left: Division, right: Terms) =
            DivisionAndAnythingMultiplication.execute(left, right)

    private class TestProduct(operands: Terms) : Product(operands) {
        override val isNegative = false


        override fun invert() = TODO("not implemented")

        override fun recreate(newOperands: Terms) = TestProduct(newOperands)

        override fun applySign() = TODO("not implemented")
    }
}