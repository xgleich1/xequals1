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


class AnythingAndDashOperationAdditionTest {
    @Test
    fun `Should add a single term to a positive dash operation`() {
        // GIVEN
        val anything = anything()
        val left = termsOf(anything)

        val operandA = anything()
        val operandB = anything()
        val right = PositiveDashOperation(operandA, operandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                anything,
                operandA,
                operandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add several terms to a positive dash operation`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val left = termsOf(anythingA, anythingB)

        val operandA = anything()
        val operandB = anything()
        val right = PositiveDashOperation(operandA, operandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                anythingA,
                anythingB,
                operandA,
                operandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a single term to a negative dash operation`() {
        // GIVEN
        val (anything, anythingOpposite) = anythingAndOpposite()
        val left = termsOf(anything)

        val operandA = anything()
        val operandB = anything()
        val right = NegativeDashOperation(operandA, operandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                anythingOpposite,
                operandA,
                operandB))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add several terms to a negative dash operation`() {
        // GIVEN
        val (anythingA, anythingOppositeA) = anythingAndOpposite()
        val (anythingB, anythingOppositeB) = anythingAndOpposite()
        val left = termsOf(anythingA, anythingB)

        val operandA = anything()
        val operandB = anything()
        val right = NegativeDashOperation(operandA, operandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                anythingOppositeA,
                anythingOppositeB,
                operandA,
                operandB))

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

    private fun add(left: Terms, right: DashOperation) =
            AnythingAndDashOperationAddition.execute(left, right)
}