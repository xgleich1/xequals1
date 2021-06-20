package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndZeroMultiplicationStepTest {
    @Test
    fun `A anything and zero multiplication step is applicable with operands containing anything followed by zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        // THEN
        assertTrue(AnythingAndZeroMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A anything and zero multiplication step is applicable with source & target selecting anything and zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndZeroMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and zero multiplication step by extracting & multiplying anything and zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(AnythingAndZeroMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and zero multiplication step by multiplying the selected anything and zero`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(0))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(AnythingAndZeroMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}