package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class TermAndOppositeTermDivisionStepTest {
    @Test
    fun `A term and opposite term division step is applicable with a term as the numerator and its opposite as the denominator`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))

        // THEN
        assertTrue(TermAndOppositeTermDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A term and opposite term division step is applicable with source & target selecting a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(TermAndOppositeTermDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a term and opposite term division step by extracting & dividing a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))

        // THEN
        val expectedResult = termsOf(NegativeValue(1))

        assertThat(TermAndOppositeTermDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a term and opposite term division step by dividing the selected term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(NegativeValue(1))

        assertThat(TermAndOppositeTermDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}