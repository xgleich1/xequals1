package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndProductMultiplicationStepTest {
    @Test
    fun `A anything and product multiplication step is applicable with operands containing anything followed by a product`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        // THEN
        assertTrue(AnythingAndProductMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A anything and product multiplication step is applicable with source & target selecting anything and a product`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(AnythingAndProductMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a anything and product multiplication step by extracting & multiplying anything and a product`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4)))

        assertThat(AnythingAndProductMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a anything and product multiplication step by multiplying the selected anything and product`() {
        // GIVEN
        val operands = termsOf(
                PositiveValue(2),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4)))

        assertThat(AnythingAndProductMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}