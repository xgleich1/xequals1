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


class DivisionAndDivisionAdditionStepTest {
    @Test
    fun `A division and division addition step is applicable with operands containing at least two divisions`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        assertTrue(DivisionAndDivisionAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A division and division addition step is applicable with source & target selecting single divisions`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DivisionAndDivisionAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a division and division addition step by extracting & adding two divisions`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveValue(1),
                                PositiveValue(4)),
                        PositiveProduct(
                                PositiveValue(2),
                                PositiveValue(3))),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4))))

        assertThat(DivisionAndDivisionAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a division and division addition step by adding the selected divisions`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveProduct(
                                PositiveValue(1),
                                PositiveValue(4)),
                        PositiveProduct(
                                PositiveValue(2),
                                PositiveValue(3))),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4))))

        assertThat(DivisionAndDivisionAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}