package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DashOperationAndAnythingAdditionStepTest {
    @Test
    fun `A dash operation and anything addition step is applicable with operands containing a dash operation followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        assertTrue(DashOperationAndAnythingAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A dash operation and anything addition step is applicable with source & target selecting a dash operation and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DashOperationAndAnythingAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a dash operation and anything addition step by extracting & adding a dash operation and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3)))

        assertThat(DashOperationAndAnythingAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a dash operation and anything addition step by adding the selected dash operation and anything`() {
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
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3)))

        assertThat(DashOperationAndAnythingAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}