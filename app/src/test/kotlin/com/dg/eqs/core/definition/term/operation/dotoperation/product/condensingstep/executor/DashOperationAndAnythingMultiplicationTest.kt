package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.NegativeProduct
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndAnythingMultiplicationTest {
    @Test
    fun `Should multiply a positive dash operation having products as operands with a single term`() {
        // GIVEN
        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val left = PositiveDashOperation(productA, productB)

        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        productA.first,
                        productA.second,
                        anythingCopies.first),
                NegativeProduct(
                        productB.first,
                        productB.second,
                        anythingCopies.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive dash operation having no products as operands with a single term`() {
        // GIVEN
        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val left = PositiveDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        dashOperationOperandA,
                        anythingCopies.first),
                PositiveProduct(
                        dashOperationOperandB,
                        anythingCopies.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive dash operation having products as operands with several terms`() {
        // GIVEN
        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val left = PositiveDashOperation(productA, productB)

        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        productA.first,
                        productA.second,
                        anythingCopiesA.first,
                        anythingCopiesB.first),
                NegativeProduct(
                        productB.first,
                        productB.second,
                        anythingCopiesA.second,
                        anythingCopiesB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive dash operation having no products as operands with several terms`() {
        // GIVEN
        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val left = PositiveDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        dashOperationOperandA,
                        anythingCopiesA.first,
                        anythingCopiesB.first),
                PositiveProduct(
                        dashOperationOperandB,
                        anythingCopiesA.second,
                        anythingCopiesB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative dash operation having products as operands with a single term`() {
        // GIVEN
        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val left = NegativeDashOperation(productA, productB)

        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        productA.first,
                        productA.second,
                        anythingCopies.first),
                NegativeProduct(
                        productB.first,
                        productB.second,
                        anythingCopies.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative dash operation having no products as operands with a single term`() {
        // GIVEN
        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val left = NegativeDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val right = termsOf(anything)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        dashOperationOperandA,
                        anythingCopies.first),
                PositiveProduct(
                        dashOperationOperandB,
                        anythingCopies.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative dash operation having products as operands with several terms`() {
        // GIVEN
        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val left = NegativeDashOperation(productA, productB)

        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        productA.first,
                        productA.second,
                        anythingCopiesA.first,
                        anythingCopiesB.first),
                NegativeProduct(
                        productB.first,
                        productB.second,
                        anythingCopiesA.second,
                        anythingCopiesB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative dash operation having no products as operands with several terms`() {
        // GIVEN
        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val left = NegativeDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val right = termsOf(anythingA, anythingB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        dashOperationOperandA,
                        anythingCopiesA.first,
                        anythingCopiesB.first),
                PositiveProduct(
                        dashOperationOperandB,
                        anythingCopiesA.second,
                        anythingCopiesB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingCopies(anything: Term): List<Term> {
        val firstCopy = anything()
        val secondCopy = anything()

        whenever(anything.copy())
                .thenReturn(firstCopy, secondCopy)

        return listOf(firstCopy, secondCopy)
    }

    private fun multiply(left: DashOperation, right: Terms) =
            DashOperationAndAnythingMultiplication.execute(left, right)
}