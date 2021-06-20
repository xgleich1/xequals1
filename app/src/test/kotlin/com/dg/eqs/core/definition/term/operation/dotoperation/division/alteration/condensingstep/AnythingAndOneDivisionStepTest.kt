package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndOneDivisionStepTest {
    @Test
    fun `A anything and one division step is applicable with anything as the numerator and a one as the denominator`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        // THEN
        assertTrue(AnythingAndOneDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A anything and one division step is applicable with source & target selecting anything and a one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndOneDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and one division step by extracting & dividing anything and a one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(AnythingAndOneDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and one division step by dividing the selected anything and one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(AnythingAndOneDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}