package com.dg.eqs.core.definition.term.operation.dotoperation.product

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Test


class PositiveProductTest {
    @Test
    fun `A positive product is not negative`() {
        assertFalse(PositiveProduct(mock(), mock()).isNegative)
    }

    @Test
    fun `Should convert a positive product to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operandC: Term = mock { on { toString() } doReturn "3" }
        val product = PositiveProduct(operandA, operandB, operandC)

        // THEN
        assertThat(product).hasToString("+*[1, 2, 3]")
    }

    @Test
    fun `Should invert the sign of a positive product`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val product = PositiveProduct(operandA, operandB)

        // THEN
        val expectedProduct = NegativeProduct(operandA, operandB)

        assertThat(product.invert()).isEqualTo(expectedProduct)
    }

    @Test
    fun `Should recreate a positive product with the new operands`() {
        // GIVEN
        val product = PositiveProduct(mock(), mock())

        val newOperandA: Term = mock()
        val newOperandB: Term = mock()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedRecreation = PositiveProduct(newOperands)

        assertThat(product.recreate(newOperands)).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should not multiply the operands with minus one when the sign of a positive product is applied`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val product = PositiveProduct(operandA, operandB)

        // THEN
        assertThat(product.applySign()).isEqualTo(product)
    }
}