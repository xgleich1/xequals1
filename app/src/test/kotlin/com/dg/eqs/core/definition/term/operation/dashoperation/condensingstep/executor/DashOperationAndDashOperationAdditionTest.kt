package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndDashOperationAdditionTest {
    @Test
    fun `Should add a positive dash operation to a positive dash operation`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = PositiveDashOperation(leftOperandA, leftOperandB)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = PositiveDashOperation(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                leftOperandA,
                leftOperandB,
                rightOperandA,
                rightOperandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative dash operation to a negative dash operation`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = NegativeDashOperation(leftOperandA, leftOperandB)

        val (rightOperandA, _) = anythingAndOpposite()
        val (rightOperandB, _) = anythingAndOpposite()
        val right = NegativeDashOperation(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                leftOperandA,
                leftOperandB,
                rightOperandA,
                rightOperandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive dash operation to a negative dash operation`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = PositiveDashOperation(leftOperandA, leftOperandB)

        val (rightOperandA, oppositeRightOperandA) = anythingAndOpposite()
        val (rightOperandB, oppositeRightOperandB) = anythingAndOpposite()
        val right = NegativeDashOperation(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                leftOperandA,
                leftOperandB,
                oppositeRightOperandA,
                oppositeRightOperandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative dash operation to a positive dash operation`() {
        // GIVEN
        val leftOperandA = anything()
        val leftOperandB = anything()
        val left = NegativeDashOperation(leftOperandA, leftOperandB)

        val (rightOperandA, oppositeRightOperandA) = anythingAndOpposite()
        val (rightOperandB, oppositeRightOperandB) = anythingAndOpposite()
        val right = PositiveDashOperation(rightOperandA, rightOperandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                leftOperandA,
                leftOperandB,
                oppositeRightOperandA,
                oppositeRightOperandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        whenever(opposite.invert())
                .thenReturn(anything)

        return anything and opposite
    }

    private fun add(left: DashOperation, right: DashOperation) =
            DashOperationAndDashOperationAddition.execute(left, right)
}