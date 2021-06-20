package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ZeroAndAnythingMultiplicationStepTest {
    @Test
    fun `A zero and anything multiplication step is applicable with operands containing zero followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        // THEN
        assertTrue(ZeroAndAnythingMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A zero and anything multiplication step is applicable with source & target selecting zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ZeroAndAnythingMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a zero and anything multiplication step by extracting & multiplying zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(ZeroAndAnythingMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a zero and anything multiplication step by multiplying the selected zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(ZeroAndAnythingMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}