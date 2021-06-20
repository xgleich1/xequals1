package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndAnythingDivisionStepTest {
    @Test
    fun `A division and anything division step is applicable with a division as the numerator and anything as the denominator`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        assertTrue(DivisionAndAnythingDivisionStep.isApplicable(operands))
    }

    @Test
    fun `A division and anything division step is applicable with source & target selecting a division and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DivisionAndAnythingDivisionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a division and anything division step by extracting & dividing a division and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3))))

        assertThat(DivisionAndAnythingDivisionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a division and anything division step by dividing the selected division and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveValue(1),
                PositiveProduct(
                        PositiveValue(2),
                        PositiveValue(3))))

        assertThat(DivisionAndAnythingDivisionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}