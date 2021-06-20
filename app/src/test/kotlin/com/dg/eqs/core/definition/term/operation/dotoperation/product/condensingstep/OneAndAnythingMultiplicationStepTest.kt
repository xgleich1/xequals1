package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class OneAndAnythingMultiplicationStepTest {
    @Test
    fun `A one and anything multiplication step is applicable with operands containing one followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveVariable("x"))

        // THEN
        assertTrue(OneAndAnythingMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A one and anything multiplication step is applicable with source & target selecting one and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(OneAndAnythingMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a one and anything multiplication step by extracting & multiplying one and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveVariable("x"))

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(OneAndAnythingMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a one and anything multiplication step by multiplying the selected one and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(1),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(OneAndAnythingMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}