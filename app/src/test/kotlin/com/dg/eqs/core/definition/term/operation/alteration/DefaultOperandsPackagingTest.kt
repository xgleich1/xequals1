package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.classes.negativeOperation
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.positiveOperation
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DefaultOperandsPackagingTest {
    @InjectMocks
    private lateinit var packaging: DefaultOperandsPackaging


    @Test
    fun `Should compare two equal default operands packagings`() {
        // GIVEN
        val packagingA = DefaultOperandsPackaging()
        val packagingB = DefaultOperandsPackaging()

        // THEN
        assertThat(packagingA).isEqualTo(packagingB)
    }

    @Test
    fun `Should unpack a single new operand when the originating operation is positive`() {
        // GIVEN
        val operation = positiveOperation(mock(), mock())

        val newOperand: Term = mock()
        val newOperands = termsOf(newOperand)

        // THEN
        assertThat(packaging.pack(operation, newOperands)).isSameAs(newOperand)
    }

    @Test
    fun `Should unpack and invert the sign of a single new operand when the originating operation is negative`() {
        // GIVEN
        val operation = negativeOperation(mock(), mock())

        val newOperandWithInvertedSign: Term = mock()
        val newOperand: Term = mock {
            on { invert() } doReturn newOperandWithInvertedSign
        }
        val newOperands = termsOf(newOperand)

        // THEN
        assertThat(packaging.pack(operation, newOperands)).isSameAs(newOperandWithInvertedSign)
    }

    @Test
    fun `Should repack the new operands when they are less numerous than the originating operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operandC: Term = mock()
        val operation = operation(operandA, operandB, operandC)

        val newOperands = termsOf(operandA, operandB)

        // THEN
        val expectedResult = operation(operandA, operandB)

        assertThat(packaging.pack(operation, newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should repack the new operands when they are more numerous than the originating operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operation = operation(operandA, operandB)

        val newOperand: Term = mock()
        val newOperands = termsOf(operandA, operandB, newOperand)

        // THEN
        val expectedResult = operation(operandA, operandB, newOperand)

        assertThat(packaging.pack(operation, newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should repack the new operands when they are differently ordered than the originating operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operation = operation(operandA, operandB)

        val newOperands = termsOf(operandB, operandA)

        // THEN
        val expectedResult = operation(operandB, operandA)

        assertThat(packaging.pack(operation, newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should repack the new operands when at least one of them is not present in the originating operands`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operation = operation(operandA, operandB)

        val newOperand: Term = mock()
        val newOperands = termsOf(operandA, newOperand)

        // THEN
        val expectedResult = operation(operandA, newOperand)

        assertThat(packaging.pack(operation, newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should not repack the new operands when no alteration was done resulting in them being the originating ones`() {
        // GIVEN
        val operandA: Term = mock()
        val operandB: Term = mock()
        val operation = operation(operandA, operandB)

        val newOperands = termsOf(operandA, operandB)

        // THEN
        assertThat(packaging.pack(operation, newOperands)).isSameAs(operation)
    }
}