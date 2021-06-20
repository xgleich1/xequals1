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


class AnythingAndDashOperationMultiplicationTest {
    @Test
    fun `Should multiply a single term with a positive dash operation having products as operands`() {
        // GIVEN
        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val left = termsOf(anything)

        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val right = PositiveDashOperation(productA, productB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        anythingCopies.first,
                        productA.first,
                        productA.second),
                NegativeProduct(
                        anythingCopies.second,
                        productB.first,
                        productB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a positive dash operation having no products as operands`() {
        // GIVEN
        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val left = termsOf(anything)

        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val right = PositiveDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        anythingCopies.first,
                        dashOperationOperandA),
                PositiveProduct(
                        anythingCopies.second,
                        dashOperationOperandB)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a positive dash operation having products as operands`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val left = termsOf(anythingA, anythingB)

        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val right = PositiveDashOperation(productA, productB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        anythingCopiesA.first,
                        anythingCopiesB.first,
                        productA.first,
                        productA.second),
                NegativeProduct(
                        anythingCopiesA.second,
                        anythingCopiesB.second,
                        productB.first,
                        productB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a positive dash operation having no products as operands`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val left = termsOf(anythingA, anythingB)

        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val right = PositiveDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        anythingCopiesA.first,
                        anythingCopiesB.first,
                        dashOperationOperandA),
                PositiveProduct(
                        anythingCopiesA.second,
                        anythingCopiesB.second,
                        dashOperationOperandB)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a negative dash operation having products as operands`() {
        // GIVEN
        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val left = termsOf(anything)

        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val right = NegativeDashOperation(productA, productB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        anythingCopies.first,
                        productA.first,
                        productA.second),
                NegativeProduct(
                        anythingCopies.second,
                        productB.first,
                        productB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a single term with a negative dash operation having no products as operands`() {
        // GIVEN
        val anything = anything()
        val anythingCopies = anythingCopies(anything)
        val left = termsOf(anything)

        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val right = NegativeDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        anythingCopies.first,
                        dashOperationOperandA),
                PositiveProduct(
                        anythingCopies.second,
                        dashOperationOperandB)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a negative dash operation having products as operands`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val left = termsOf(anythingA, anythingB)

        val productA = PositiveProduct(anything(), anything())
        val productB = NegativeProduct(anything(), anything())
        val right = NegativeDashOperation(productA, productB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        anythingCopiesA.first,
                        anythingCopiesB.first,
                        productA.first,
                        productA.second),
                NegativeProduct(
                        anythingCopiesA.second,
                        anythingCopiesB.second,
                        productB.first,
                        productB.second)))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply several terms with a negative dash operation having no products as operands`() {
        // GIVEN
        val anythingA = anything()
        val anythingB = anything()
        val anythingCopiesA = anythingCopies(anythingA)
        val anythingCopiesB = anythingCopies(anythingB)
        val left = termsOf(anythingA, anythingB)

        val dashOperationOperandA = anything()
        val dashOperationOperandB = anything()
        val right = NegativeDashOperation(
                dashOperationOperandA,
                dashOperationOperandB)

        // THEN
        val expectedResult = termsOf(NegativeDashOperation(
                PositiveProduct(
                        anythingCopiesA.first,
                        anythingCopiesB.first,
                        dashOperationOperandA),
                PositiveProduct(
                        anythingCopiesA.second,
                        anythingCopiesB.second,
                        dashOperationOperandB)))

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

    private fun multiply(left: Terms, right: DashOperation) =
            AnythingAndDashOperationMultiplication.execute(left, right)
}