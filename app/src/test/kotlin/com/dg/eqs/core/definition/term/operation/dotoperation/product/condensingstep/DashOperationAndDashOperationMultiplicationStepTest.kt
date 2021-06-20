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


class DashOperationAndDashOperationMultiplicationStepTest {
    @Test
    fun `A dash operation and dash operation multiplication step is applicable with operands containing at least two dash operations`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        assertTrue(DashOperationAndDashOperationMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A dash operation and dash operation multiplication step is applicable with source & target selecting single dash operations`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DashOperationAndDashOperationMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a dash operation and dash operation multiplication step by extracting & multiplying two dash operations`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(4))))

        assertThat(DashOperationAndDashOperationMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a dash operation and dash operation multiplication step by multiplying the selected dash operations`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveDashOperation(
                                PositiveValue(1),
                                PositiveValue(2)),
                        PositiveValue(4))))

        assertThat(DashOperationAndDashOperationMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}