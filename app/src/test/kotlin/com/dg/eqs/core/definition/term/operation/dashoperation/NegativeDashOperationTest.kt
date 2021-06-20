package com.dg.eqs.core.definition.term.operation.dashoperation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeDashOperationTest {
    @Test
    fun `A negative dash operation is negative`() {
        assertTrue(NegativeDashOperation(anything(), anything()).isNegative)
    }

    @Test
    fun `Should convert a negative dash operation to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operandC: Term = mock { on { toString() } doReturn "3" }
        val dashOperation = NegativeDashOperation(operandA, operandB, operandC)

        // THEN
        assertThat(dashOperation).hasToString("-±[1, 2, 3]")
    }

    @Test
    fun `Should invert the minus sign of a negative dash operation to a plus sign`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = NegativeDashOperation(operandA, operandB)

        // THEN
        val expectedDashOperation = PositiveDashOperation(operandA, operandB)

        assertThat(dashOperation.invert()).isEqualTo(expectedDashOperation)
    }

    @Test
    fun `Should recreate a negative dash operation with the new operands`() {
        // GIVEN
        val dashOperation = NegativeDashOperation(anything(), anything())

        val newOperands = termsOf(anything(), anything())

        // THEN
        val expectedRecreation = NegativeDashOperation(newOperands)

        assertThat(dashOperation.recreate(newOperands)).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should apply the sign of a negative dash operation by inverting all operands signs`() {
        // GIVEN
        val (operandA, oppositeOperandA) = anythingAndOpposite()
        val (operandB, oppositeOperandB) = anythingAndOpposite()
        val dashOperation = NegativeDashOperation(operandA, operandB)

        // THEN
        val expectedDashOperation = PositiveDashOperation(
                oppositeOperandA,
                oppositeOperandB)

        assertThat(dashOperation.applySign()).isEqualTo(expectedDashOperation)
    }

    @Test
    fun `Should add new operands to the front of a negative dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = NegativeDashOperation(operandA, operandB)

        val (newOperandA, oppositeNewOperandA) = anythingAndOpposite()
        val (newOperandB, oppositeNewOperandB) = anythingAndOpposite()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = NegativeDashOperation(
                oppositeNewOperandA,
                oppositeNewOperandB,
                operandA,
                operandB)

        assertThat(dashOperation.addToFront(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add new operands to the back of a negative dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = NegativeDashOperation(operandA, operandB)

        val (newOperandA, oppositeNewOperandA) = anythingAndOpposite()
        val (newOperandB, oppositeNewOperandB) = anythingAndOpposite()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = NegativeDashOperation(
                operandA,
                operandB,
                oppositeNewOperandA,
                oppositeNewOperandB)

        assertThat(dashOperation.addToBack(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should subtract new operands from the front of a negative dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = NegativeDashOperation(operandA, operandB)

        val newOperandA = anything()
        val newOperandB = anything()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = NegativeDashOperation(
                newOperandA,
                newOperandB,
                operandA,
                operandB)

        assertThat(dashOperation.subtractFromFront(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should subtract new operands from the back of a negative dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = NegativeDashOperation(operandA, operandB)

        val newOperandA = anything()
        val newOperandB = anything()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = NegativeDashOperation(
                operandA,
                operandB,
                newOperandA,
                newOperandB)

        assertThat(dashOperation.subtractFromBack(newOperands)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }
}