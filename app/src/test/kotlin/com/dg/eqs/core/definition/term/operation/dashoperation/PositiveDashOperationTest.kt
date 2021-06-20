package com.dg.eqs.core.definition.term.operation.dashoperation

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Test


class PositiveDashOperationTest {
    @Test
    fun `A positive dash operation is not negative`() {
        assertFalse(PositiveDashOperation(anything(), anything()).isNegative)
    }

    @Test
    fun `Should convert a positive dash operation to a string`() {
        // GIVEN
        val operandA: Term = mock { on { toString() } doReturn "1" }
        val operandB: Term = mock { on { toString() } doReturn "2" }
        val operandC: Term = mock { on { toString() } doReturn "3" }
        val dashOperation = PositiveDashOperation(operandA, operandB, operandC)

        // THEN
        assertThat(dashOperation).hasToString("+±[1, 2, 3]")
    }

    @Test
    fun `Should invert the plus sign of a positive dash operation to a minus sign`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = PositiveDashOperation(operandA, operandB)

        // THEN
        val expectedDashOperation = NegativeDashOperation(operandA, operandB)

        assertThat(dashOperation.invert()).isEqualTo(expectedDashOperation)
    }

    @Test
    fun `Should recreate a positive dash operation with the new operands`() {
        // GIVEN
        val dashOperation = PositiveDashOperation(anything(), anything())

        val newOperandA = anything()
        val newOperandB = anything()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedRecreation = PositiveDashOperation(newOperands)

        assertThat(dashOperation.recreate(newOperands)).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should not invert any signs when the sign of a positive dash operation is applied`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = PositiveDashOperation(operandA, operandB)

        // THEN
        assertThat(dashOperation.applySign()).isEqualTo(dashOperation)
    }

    @Test
    fun `Should add new operands to the front of a positive dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = PositiveDashOperation(operandA, operandB)

        val newOperandA = anything()
        val newOperandB = anything()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = PositiveDashOperation(
                newOperandA,
                newOperandB,
                operandA,
                operandB)

        assertThat(dashOperation.addToFront(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add new operands to the back of a positive dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = PositiveDashOperation(operandA, operandB)

        val newOperandA = anything()
        val newOperandB = anything()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = PositiveDashOperation(
                operandA,
                operandB,
                newOperandA,
                newOperandB)

        assertThat(dashOperation.addToBack(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should subtract new operands from the front of a positive dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = PositiveDashOperation(operandA, operandB)

        val (newOperandA, oppositeNewOperandA) = anythingAndOpposite()
        val (newOperandB, oppositeNewOperandB) = anythingAndOpposite()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = PositiveDashOperation(
                oppositeNewOperandA,
                oppositeNewOperandB,
                operandA,
                operandB)

        assertThat(dashOperation.subtractFromFront(newOperands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should subtract new operands from the back of a positive dash operation`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val dashOperation = PositiveDashOperation(operandA, operandB)

        val (newOperandA, oppositeNewOperandA) = anythingAndOpposite()
        val (newOperandB, oppositeNewOperandB) = anythingAndOpposite()
        val newOperands = termsOf(newOperandA, newOperandB)

        // THEN
        val expectedResult = PositiveDashOperation(
                operandA,
                operandB,
                oppositeNewOperandA,
                oppositeNewOperandB)

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