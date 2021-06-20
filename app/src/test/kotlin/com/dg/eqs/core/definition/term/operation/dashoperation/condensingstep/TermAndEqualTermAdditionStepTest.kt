package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.variable.PositiveVariable
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class TermAndEqualTermAdditionStepTest {
    @Test
    fun `A term and equal term addition step is applicable with operands containing at least two equal terms`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        // THEN
        assertTrue(TermAndEqualTermAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A term and equal term addition step is applicable with source & target selecting a term and its equal`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(TermAndEqualTermAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a term and equal term addition step by extracting & adding a term and its equal`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(2),
                PositiveVariable("x")))

        assertThat(TermAndEqualTermAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a term and equal term addition step by adding the selected term and its equal`() {
        // GIVEN
        val operands = termsOf(
                PositiveVariable("x"),
                PositiveVariable("x"))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(2),
                PositiveVariable("x")))

        assertThat(TermAndEqualTermAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}