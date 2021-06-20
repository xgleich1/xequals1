package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndEqualDenominatorDivisionAdditionStepTest {
    @Test
    fun `A division and equal denominator division addition step is applicable with operands containing at least two divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))
        // THEN
        assertTrue(DivisionAndEqualDenominatorDivisionAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A division and equal denominator division addition step is applicable with source & target selecting single divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DivisionAndEqualDenominatorDivisionAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a division and equal denominator division addition step by extracting & adding two divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))
        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3)))

        assertThat(DivisionAndEqualDenominatorDivisionAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a division and equal denominator division addition step by adding the selected divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(3)),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(3)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3)))

        assertThat(DivisionAndEqualDenominatorDivisionAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}