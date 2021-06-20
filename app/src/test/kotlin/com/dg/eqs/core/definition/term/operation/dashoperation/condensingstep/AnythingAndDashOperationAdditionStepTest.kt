package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndDashOperationAdditionStepTest {
    @Test
    fun `A anything and dash operation addition step is applicable with operands containing anything followed by a dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)))

        // THEN
        assertTrue(AnythingAndDashOperationAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A anything and dash operation addition step is applicable with source & target selecting anything and a dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndDashOperationAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and dash operation addition step by extracting & adding anything and a dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)))

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3)))

        assertThat(AnythingAndDashOperationAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and dash operation addition step by adding the selected anything and dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3)))

        assertThat(AnythingAndDashOperationAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}