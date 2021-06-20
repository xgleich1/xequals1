package com.dg.eqs.core.definition.term.operation.dotoperation.product

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeProductTest {
    @Test
    fun `A negative product is negative`() {
        assertTrue(NegativeProduct(mock(), mock()).isNegative)
    }

    @Test
    fun `Should convert a negative product to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operandC: Term = mock { on { toString() } doReturn "3" }
        val product = NegativeProduct(operandA, operandB, operandC)

        // THEN
        assertThat(product).hasToString("-*[1, 2, 3]")
    }

    @Test
    fun `Should invert the sign of a negative product`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val product = NegativeProduct(operandA, operandB)

        // THEN
        val expectedProduct = PositiveProduct(operandA, operandB)

        assertThat(product.invert()).isEqualTo(expectedProduct)
    }

    @Test
    fun `Should recreate a negative product with the new operands`() {
        // GIVEN
        val product = NegativeProduct(mock(), mock())

        val newOperandA: Term = mock()
        val newOperandB: Term = mock()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedRecreation = NegativeProduct(newOperands)

        assertThat(product.recreate(newOperands)).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should multiply the operands with minus one when the sign of a negative product is applied`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val product = NegativeProduct(operandA, operandB)

        // THEN
        val expectedResult = PositiveProduct(
                NegativeValue(1),
                operandA,
                operandB)

        assertThat(product.applySign()).isEqualTo(expectedResult)
    }
}