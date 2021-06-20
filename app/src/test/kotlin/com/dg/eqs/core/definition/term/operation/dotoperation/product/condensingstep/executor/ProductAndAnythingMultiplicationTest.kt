package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductAndAnythingMultiplicationTest {
    @Test
    fun `Should multiply a positive product with a single term`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = PositiveProduct(operandA, operandB)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                operandA,
                operandB,
                anything))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive product with several terms`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = PositiveProduct(operandA, operandB)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                operandA,
                operandB,
                anythingA,
                anythingB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative product with a single term`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = NegativeProduct(operandA, operandB)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(NegativeProduct(
                operandA,
                operandB,
                anything))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative product with several terms`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = NegativeProduct(operandA, operandB)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(NegativeProduct(
                operandA,
                operandB,
                anythingA,
                anythingB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun multiply(left: Product, right: Terms) =
            ProductAndAnythingMultiplication.execute(left, right)
}