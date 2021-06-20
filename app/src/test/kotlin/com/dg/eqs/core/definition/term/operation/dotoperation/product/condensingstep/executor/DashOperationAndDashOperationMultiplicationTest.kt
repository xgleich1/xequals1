package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndDashOperationMultiplicationTest {
    @Test
    fun `Should multiply a dash operation with a positive dash operation having products as operands`() {
        // GIVEN
        val left = dashOperation()
        val leftCopies = dashOperationCopies(left)

        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val right = PositiveDashOperation(productA, productB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        leftCopies.first,
                        productA.first,
                        productA.second),
                NegativeProduct(
                        leftCopies.second,
                        productB.first,
                        productB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a dash operation with a positive dash operation having no products as operands`() {
        // GIVEN
        val left = dashOperation()
        val leftCopies = dashOperationCopies(left)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = PositiveDashOperation(
                rightOperandA,
                rightOperandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        leftCopies.first,
                        rightOperandA),
                PositiveProduct(
                        leftCopies.second,
                        rightOperandB)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a dash operation with a negative dash operation having products as operands`() {
        // GIVEN
        val left = dashOperation()
        val leftCopies = dashOperationCopies(left)

        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val right = NegativeDashOperation(productA, productB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        leftCopies.first,
                        productA.first,
                        productA.second),
                NegativeProduct(
                        leftCopies.second,
                        productB.first,
                        productB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a dash operation with a negative dash operation having no products as operands`() {
        // GIVEN
        val left = dashOperation()
        val leftCopies = dashOperationCopies(left)

        val rightOperandA = anything()
        val rightOperandB = anything()
        val right = NegativeDashOperation(
                rightOperandA,
                rightOperandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        leftCopies.first,
                        rightOperandA),
                PositiveProduct(
                        leftCopies.second,
                        rightOperandB)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun dashOperation(): DashOperation = mock()

    private fun dashOperationCopies(
            dashOperation: DashOperation): List<DashOperation> {

        val firstCopy = dashOperation()
        val secondCopy = dashOperation()

        whenever(dashOperation.copy())
                .thenReturn(firstCopy, secondCopy)

        return listOf(firstCopy, secondCopy)
    }

    private fun anything(): Term = mock()

    private fun multiply(left: DashOperation, right: DashOperation) =
            DashOperationAndDashOperationMultiplication.execute(left, right)
}