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


class AnythingAndDashOperationMultiplicationStepTest {
    @Test
    fun `A anything and dash operation multiplication step is applicable with operands containing anything followed by a dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        // THEN
        assertTrue(AnythingAndDashOperationMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A anything and dash operation multiplication step is applicable with source & target selecting anything and a dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndDashOperationMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and dash operation multiplication step by extracting & multiplying anything and a dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4))))

        assertThat(AnythingAndDashOperationMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and dash operation multiplication step by multiplying the selected anything and dash operation`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDashOperation(
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4))))

        assertThat(AnythingAndDashOperationMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}