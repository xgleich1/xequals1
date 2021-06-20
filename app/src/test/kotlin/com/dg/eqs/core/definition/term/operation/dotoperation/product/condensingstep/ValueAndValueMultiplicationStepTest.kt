package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ValueAndValueMultiplicationStepTest {
    @Test
    fun `A values and value multiplication step is applicable with operands containing at least two values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        assertTrue(ValueAndValueMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A values and value multiplication step is applicable with source & target selecting single values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ValueAndValueMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a value and value multiplication step by extracting & multiplying two values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveValue(6))

        assertThat(ValueAndValueMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a value and value multiplication step by multiplying the selected values`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(6))

        assertThat(ValueAndValueMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}