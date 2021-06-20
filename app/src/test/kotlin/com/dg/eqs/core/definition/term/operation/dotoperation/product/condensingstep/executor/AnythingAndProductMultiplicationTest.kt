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


class AnythingAndProductMultiplicationTest {
    @Test
    fun `Should multiply a single term with a positive product`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val operandA = anything()
        val operandB = anything()
        val right = PositiveProduct(operandA, operandB)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                anything,
                operandA,
                operandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a positive product`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val operandA = anything()
        val operandB = anything()
        val right = PositiveProduct(operandA, operandB)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                anythingA,
                anythingB,
                operandA,
                operandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a negative product`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val operandA = anything()
        val operandB = anything()
        val right = NegativeProduct(operandA, operandB)

        // THEN
        val expectedResult = termsOf(NegativeProduct(
                anything,
                operandA,
                operandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a negative product`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val operandA = anything()
        val operandB = anything()
        val right = NegativeProduct(operandA, operandB)

        // THEN
        val expectedResult = termsOf(NegativeProduct(
                anythingA,
                anythingB,
                operandA,
                operandB))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun multiply(left: Terms, right: Product) =
            AnythingAndProductMultiplication.execute(left, right)
}