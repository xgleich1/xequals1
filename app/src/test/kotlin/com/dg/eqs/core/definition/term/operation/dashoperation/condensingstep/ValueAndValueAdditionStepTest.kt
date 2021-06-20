package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ValueAndValueAdditionStepTest {
    @Test
    fun `A value and value addition step is applicable with operands containing at least two values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveValue(2))

        // THEN
        assertTrue(ValueAndValueAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A value and value addition step is applicable with source & target selecting single values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveValue(2))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ValueAndValueAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a value and value addition step by extracting & adding two values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveValue(2))

        // THEN
        val expectedResult = termsOf(PositiveValue(3))

        assertThat(ValueAndValueAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a value and value addition step by adding the selected values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveValue(2))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(3))

        assertThat(ValueAndValueAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}