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


class AnythingAndDivisionThroughPositiveOneAdditionStepTest {
    @Test
    fun `A anything and division through positive one addition step is applicable with operands containing anything followed by a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(3),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A anything and division through positive one addition step is applicable with source & target selecting anything and a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(3),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and division through positive one addition step by extracting & adding anything and a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(3),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1)))

        assertThat(AnythingAndDivisionThroughPositiveOneAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and division through positive one addition step by adding the selected anything and division through positive one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(3),
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(3),
                        PositiveValue(2)),
                PositiveValue(1)))

        assertThat(AnythingAndDivisionThroughPositiveOneAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}