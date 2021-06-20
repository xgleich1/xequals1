package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ZeroAndAnythingAdditionStepTest {
    @Test
    fun `A zero and anything addition step is applicable with operands containing zero followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        // THEN
        assertTrue(ZeroAndAnythingAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A zero and anything addition step is applicable with source & target selecting zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ZeroAndAnythingAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a zero and anything addition step by extracting & adding zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(ZeroAndAnythingAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a zero and anything addition step by adding the selected zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveVariable("x"))

        assertThat(ZeroAndAnythingAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}