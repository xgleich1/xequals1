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


class PositiveTermAndNegativeTermDivisionStepTest {
    @Test
    fun `A positive term and negative term division step is applicable with a positive term as the numerator & a negative term as the denominator`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("y"))

        // THEN
        assertTrue(PositiveTermAndNegativeTermDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A positive term and negative term division step is applicable with source & target selecting a positive term and a negative term`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(PositiveTermAndNegativeTermDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a positive term and negative term division step by extracting & dividing a positive term and a negative term`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("y"))

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveVariable("x"),
                PositiveVariable("y")))

        assertThat(PositiveTermAndNegativeTermDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a positive term and negative term division step by dividing the selected positive term and negative term`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("y"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveVariable("x"),
                PositiveVariable("y")))

        assertThat(PositiveTermAndNegativeTermDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}