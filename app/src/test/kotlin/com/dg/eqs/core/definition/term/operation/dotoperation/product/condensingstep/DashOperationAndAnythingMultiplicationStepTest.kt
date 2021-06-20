package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DashOperationAndAnythingMultiplicationStepTest {
    @Test
    fun `A dash operation and anything multiplication step is applicable with operands containing a dash operation followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        assertTrue(DashOperationAndAnythingMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A dash operation and anything multiplication step is applicable with source & target selecting a dash operation and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DashOperationAndAnythingMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a dash operation and anything multiplication step by extracting & multiplying a dash operation and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3))))

        assertThat(DashOperationAndAnythingMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a dash operation and anything multiplication step by multiplying the selected dash operation and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3))))

        assertThat(DashOperationAndAnythingMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}