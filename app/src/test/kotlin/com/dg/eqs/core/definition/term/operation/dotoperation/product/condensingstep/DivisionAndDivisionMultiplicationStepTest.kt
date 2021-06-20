package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndDivisionMultiplicationStepTest {
    @Test
    fun `A division and division multiplication step is applicable with operands containing at least two divisions`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveDivision(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        assertTrue(DivisionAndDivisionMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A division and division multiplication step is applicable with source & target selecting single divisions`() {
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
        assertTrue(DivisionAndDivisionMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a division and division multiplication step by extracting & multiplying two divisions`() {
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
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4))))

        assertThat(DivisionAndDivisionMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a division and division multiplication step by multiplying the selected divisions`() {
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
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(4))))

        assertThat(DivisionAndDivisionMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}