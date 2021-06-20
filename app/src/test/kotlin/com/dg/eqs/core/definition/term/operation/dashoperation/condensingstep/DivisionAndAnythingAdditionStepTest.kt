package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndAnythingAdditionStepTest {
    @Test
    fun `A division and anything addition step is applicable with operands containing a division followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        assertTrue(DivisionAndAnythingAdditionStep.isApplicable(operands))
    }

    @Test
    fun `A division and anything addition step is applicable with source & target selecting a division and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(DivisionAndAnythingAdditionStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a division and anything addition step by extracting & adding a division and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveDivision(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveProduct(
                                PositiveValue(2),
                                PositiveValue(3))),
                PositiveValue(2)))

        assertThat(DivisionAndAnythingAdditionStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a division and anything addition step by adding the selected division and anything`() {
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
                PositiveDashOperation(
                        PositiveValue(1),
                        PositiveProduct(
                                PositiveValue(2),
                                PositiveValue(3))),
                PositiveValue(2)))

        assertThat(DivisionAndAnythingAdditionStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}