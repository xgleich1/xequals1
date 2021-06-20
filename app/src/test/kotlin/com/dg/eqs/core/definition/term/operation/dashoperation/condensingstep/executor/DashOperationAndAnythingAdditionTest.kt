package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndAnythingAdditionTest {
    @Test
    fun `Should add a positive dash operation to a single term`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = PositiveDashOperation(operandA, operandB)

        val anything = anything()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                operandA,
                operandB,
                anything))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive dash operation to several terms`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = PositiveDashOperation(operandA, operandB)

        val anythingA = anything()
        val anythingB = anything()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                operandA,
                operandB,
                anythingA,
                anythingB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative dash operation to a single term`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = NegativeDashOperation(operandA, operandB)

        val (anything, anythingOpposite) = anythingAndOpposite()
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                operandA,
                operandB,
                anythingOpposite))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative dash operation to several terms`() {
        // GIVEN
        val operandA = anything()
        val operandB = anything()
        val left = NegativeDashOperation(operandA, operandB)

        val (anythingA, anythingOppositeA) = anythingAndOpposite()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                operandA,
                operandB,
                anythingOppositeA,
                anythingOppositeB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }

    private fun add(left: DashOperation, right: Terms) =
            DashOperationAndAnythingAddition.execute(left, right)
}