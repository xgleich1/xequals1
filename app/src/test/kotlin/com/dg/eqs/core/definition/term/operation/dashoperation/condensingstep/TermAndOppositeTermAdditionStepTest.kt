package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.NegativeVariable
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class TermAndOppositeTermAdditionStepTest {
    @Test
    fun `A term and opposite term addition step is applicable with operands containing a pair of a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))
        // THEN
        assertTrue(TermAndOppositeTermAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A term and opposite term addition step is applicable with source & target selecting a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(TermAndOppositeTermAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a term and opposite term addition step by extracting & adding a pair of a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))
        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(TermAndOppositeTermAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a term and opposite term addition step by adding the selected pair of a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                NegativeVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveValue(0))

        assertThat(TermAndOppositeTermAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}