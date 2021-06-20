package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DashOperationAndDashOperationAdditionStepTest {
    @Test
    fun `A dash operation and dash operation addition step is applicable with operands containing at least two dash operations`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        assertTrue(DashOperationAndDashOperationAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A dash operation and dash operation addition step is applicable with source & target selecting single dash operations`() {
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
        assertTrue(DashOperationAndDashOperationAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a dash operation and dash operation addition step by extracting & adding two dash operations`() {
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
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4)))

        assertThat(DashOperationAndDashOperationAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a dash operation and dash operation addition step by adding the selected dash operations`() {
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
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4)))

        assertThat(DashOperationAndDashOperationAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}