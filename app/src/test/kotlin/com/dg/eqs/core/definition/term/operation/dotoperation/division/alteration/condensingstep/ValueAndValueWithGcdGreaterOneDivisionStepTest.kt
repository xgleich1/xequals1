package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ValueAndValueWithGcdGreaterOneDivisionStepTest {
    @Test
    fun `A value and value with gcd greater one division step is applicable with a value as the numerator and a value as the denominator having an gcd greater one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(4),
                PositiveValue(2))

        // THEN
        assertTrue(ValueAndValueWithGcdGreaterOneDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A value and value with gcd greater one division step is applicable with source & target selecting a value and a value having an gcd greater one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(4),
                PositiveValue(2))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ValueAndValueWithGcdGreaterOneDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a value and value with gcd greater one division step by extracting & dividing a value and a value having an gcd greater one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(4),
                PositiveValue(2))

        // THEN
        val expectedResult = termsOf(PositiveValue(2))

        assertThat(ValueAndValueWithGcdGreaterOneDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a value and value with gcd greater one division step by dividing the selected value and value having an gcd greater one`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(4),
                PositiveValue(2))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(2))

        assertThat(ValueAndValueWithGcdGreaterOneDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}