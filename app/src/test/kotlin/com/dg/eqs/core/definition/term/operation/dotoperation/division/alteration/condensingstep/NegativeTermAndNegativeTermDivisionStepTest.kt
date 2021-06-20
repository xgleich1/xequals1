package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeTermAndNegativeTermDivisionStepTest {
    @Test
    fun `A negative term and negative term division step is applicable with a negative term as the numerator & a negative term as the denominator`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                NegativeVariable("y"))

        // THEN
        assertTrue(NegativeTermAndNegativeTermDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A negative term and negative term division step is applicable with source & target selecting a negative term and a negative term`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(NegativeTermAndNegativeTermDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a negative term and negative term division step by extracting & dividing a negative term and a negative term`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                NegativeVariable("y"))

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveVariable("x"),
                PositiveVariable("y")))

        assertThat(NegativeTermAndNegativeTermDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a negative term and negative term division step by dividing the selected negative term and negative term`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveVariable("x"),
                PositiveVariable("y")))

        assertThat(NegativeTermAndNegativeTermDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}