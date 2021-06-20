package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndZeroAdditionStepTest {
    @Test
    fun `A anything and zero addition step is applicable with operands containing anything followed by zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        // THEN
        assertTrue(AnythingAndZeroAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A anything and zero addition step is applicable with source & target selecting anything and zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndZeroAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and zero addition step by extracting & adding anything and zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(AnythingAndZeroAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and zero addition step by adding the selected anything and zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(AnythingAndZeroAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}