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


class DivisionThroughPositiveOneAndAnythingAdditionStepTest {
    @Test
    fun `A division through positive one and anything addition step is applicable with operands containing a division through positive one followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(3))

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A division through positive one and anything addition step is applicable with source & target selecting a division through positive one and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a division through positive one and anything addition step by extracting & adding a division through positive one and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(1)))

        assertThat(DivisionThroughPositiveOneAndAnythingAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a division through positive one and anything addition step by adding the selected division through positive one and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(2),
                        PositiveValue(1)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(2),
                        PositiveValue(3)),
                PositiveValue(1)))

        assertThat(DivisionThroughPositiveOneAndAnythingAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}