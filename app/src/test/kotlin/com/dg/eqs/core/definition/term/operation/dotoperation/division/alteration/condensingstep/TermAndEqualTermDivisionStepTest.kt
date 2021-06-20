package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class TermAndEqualTermDivisionStepTest {
    @Test
    fun `A term and equal term division step is applicable with a term as the numerator and its equal as the denominator`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        // THEN
        assertTrue(TermAndEqualTermDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A term and equal term division step is applicable with source & target selecting a term and its equal`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(TermAndEqualTermDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a term and equal term division step by extracting & dividing a term and its equal`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        // THEN
        val expectedResult = termsOf(PositiveValue(1))

        assertThat(TermAndEqualTermDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a term and equal term division step by dividing the selected term and its equal`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(1))

        assertThat(TermAndEqualTermDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}