package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ProductAndProductMultiplicationStepTest {
    @Test
    fun `A product and product multiplication step is applicable with operands containing at least two products`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        assertTrue(ProductAndProductMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A product and product multiplication step is applicable with source & target selecting single products`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ProductAndProductMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a product and product multiplication step by extracting & multiplying two products`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))
        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4)))

        assertThat(ProductAndProductMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a product and product multiplication step by multiplying the selected products`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveProduct(
                        PositiveValue(3),
                        PositiveValue(4)))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3),
                PositiveValue(4)))

        assertThat(ProductAndProductMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}