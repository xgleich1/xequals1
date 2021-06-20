package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductAndProductMultiplicationTest {
    @Test
    fun `Should multiply a positive product with a positive product`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = PositiveProduct(leftOperandA, leftOperandB)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = PositiveProduct(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                leftOperandA,
                leftOperandB,
                rightOperandA,
                rightOperandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative product with a negative product`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = NegativeProduct(leftOperandA, leftOperandB)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = NegativeProduct(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                leftOperandA,
                leftOperandB,
                rightOperandA,
                rightOperandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive product with a negative product`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = PositiveProduct(leftOperandA, leftOperandB)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = NegativeProduct(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(NegativeProduct(
                leftOperandA,
                leftOperandB,
                rightOperandA,
                rightOperandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative product with a positive product`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = NegativeProduct(leftOperandA, leftOperandB)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = PositiveProduct(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(NegativeProduct(
                leftOperandA,
                leftOperandB,
                rightOperandA,
                rightOperandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun multiply(left: Product, right: Product) =
            ProductAndProductMultiplication.execute(left, right)
}