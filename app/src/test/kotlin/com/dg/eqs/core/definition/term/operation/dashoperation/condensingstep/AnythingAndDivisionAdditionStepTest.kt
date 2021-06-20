package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndDivisionAdditionStepTest {
    @Test
    fun `A anything and division addition step is applicable with operands containing anything followed by a division`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        // THEN
        assertTrue(AnythingAndDivisionAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A anything and division addition step is applicable with source & target selecting anything and a division`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndDivisionAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and division addition step by extracting & adding anything and a division`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveValue(1),
                                PositiveValue(3)),
                        PositiveValue(2)),
                PositiveValue(3)))

        assertThat(AnythingAndDivisionAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and division addition step by adding the selected anything and division`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveValue(1),
                                PositiveValue(3)),
                        PositiveValue(2)),
                PositiveValue(3)))

        assertThat(AnythingAndDivisionAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}