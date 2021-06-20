package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndOneMultiplicationStepTest {
    @Test
    fun `A anything and one multiplication step is applicable with operands containing anything followed by one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        // THEN
        assertTrue(AnythingAndOneMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A anything and one multiplication step is applicable with source & target selecting anything and one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndOneMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and one multiplication step by extracting & multiplying anything and one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(AnythingAndOneMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and one multiplication step by multiplying the selected anything and one`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveValue(1))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(AnythingAndOneMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}