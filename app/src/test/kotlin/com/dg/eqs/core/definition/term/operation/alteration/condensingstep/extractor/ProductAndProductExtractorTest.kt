package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductAndProductExtractorTest {
    @Test
    fun `Should extract the first product as the left & the second as the right side from operands containing exactly two products`() {
        // GIVEN
        val productA = product()
        val productB = product()
        val operands = termsOf(
                anything(),
                productA,
                anything(),
                productB,
                anything())

        // THEN
        val expectedLeft = termsOf(productA)
        val expectedRight = termsOf(productB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first product as the left & the second as the right side from operands containing more than two products`() {
        // GIVEN
        val productA = product()
        val productB = product()
        val operands = termsOf(
                anything(),
                productA,
                anything(),
                productB,
                anything(),
                product(),
                anything())

        // THEN
        val expectedLeft = termsOf(productA)
        val expectedRight = termsOf(productB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun product(): Product = mock()

    private fun extract(operands: Terms) = ProductAndProductExtractor.extract(operands)
}