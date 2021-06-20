package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ZeroAndAnythingDivisionStepTest {
    @Test
    fun `A zero and anything division step is applicable with a zero as the numerator and anything as the denominator`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        // THEN
        assertTrue(ZeroAndAnythingDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A zero and anything division step is applicable with source & target selecting a zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ZeroAndAnythingDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a zero and anything division step by extracting & dividing a zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(ZeroAndAnythingDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a zero and anything division step by dividing the selected zero and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(0),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(ZeroAndAnythingDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}