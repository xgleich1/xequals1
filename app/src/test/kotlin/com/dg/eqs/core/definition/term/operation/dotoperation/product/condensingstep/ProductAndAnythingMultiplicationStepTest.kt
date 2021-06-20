package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class ProductAndAnythingMultiplicationStepTest {
    @Test
    fun `A product and anything multiplication step is applicable with operands containing a product followed by anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        assertTrue(ProductAndAnythingMultiplicationStep.isApplicable(operands))
    }

    @Test
    fun `A product and anything multiplication step is applicable with source & target selecting a product and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        assertTrue(ProductAndAnythingMultiplicationStep.isApplicable(operands, source, target))
    }

    @Test
    fun `Should apply a product and anything multiplication step by extracting & multiplying a product and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3)))

        assertThat(ProductAndAnythingMultiplicationStep.apply(operands)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should apply a product and anything multiplication step by multiplying the selected product and anything`() {
        // GIVEN
        val operands = termsOf(
                PositiveProduct(
                        PositiveValue(1),
                        PositiveValue(2)),
                PositiveValue(3))

        val source = termsOf(operands.first)
        val target = termsOf(operands.second)

        // THEN
        val expectedResult = termsOf(PositiveProduct(
                PositiveValue(1),
                PositiveValue(2),
                PositiveValue(3)))

        assertThat(ProductAndAnythingMultiplicationStep.apply(operands, source, target)).isEqualTo(expectedResult)
    }
}