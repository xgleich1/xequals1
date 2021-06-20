package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeTermAndPositiveTermDivisionStepTest {
    @Test
    fun `A negative term and positive term division step is applicable with a negative term as the numerator & a positive term as the denominator`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                PositiveVariable("y"))

        // THEN
        assertTrue(NegativeTermAndPositiveTermDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A negative term and positive term division step is applicable with source & target selecting a negative term and a positive term`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(NegativeTermAndPositiveTermDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a negative term and positive term division step by extracting & dividing a negative term and a positive term`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                PositiveVariable("y"))

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveVariable("x"),
                PositiveVariable("y")))

        assertThat(NegativeTermAndPositiveTermDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a negative term and positive term division step by dividing the selected negative term and positive term`() {
        // GIVEN
        val operands = termsOf(
                NegativeVariable("x"),
                PositiveVariable("y"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveVariable("x"),
                PositiveVariable("y")))

        assertThat(NegativeTermAndPositiveTermDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}