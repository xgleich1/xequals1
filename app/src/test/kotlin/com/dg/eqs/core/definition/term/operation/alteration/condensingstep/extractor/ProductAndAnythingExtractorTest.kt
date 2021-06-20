package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductAndAnythingExtractorTest {
    @Test
    fun `Should extract the product as the left & its successor as the right side from operands containing a product followed by anything`() {
        // GIVEN
        val product = product()
        val anything = anything()
        val operands = termsOf(
                product,
                anything,
                anything())

        // THEN
        val expectedLeft = termsOf(product)
        val expectedRight = termsOf(anything)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first product as the left & the second as the right side from operands containing a product followed by a product`() {
        // GIVEN
        val productA = product()
        val productB = product()
        val operands = termsOf(
                anything(),
                productA,
                productB)

        // THEN
        val expectedLeft = termsOf(productA)
        val expectedRight = termsOf(productB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first product as the left & the second as the right side from operands containing exclusively products`() {
        // GIVEN
        val productA = product()
        val productB = product()
        val operands = termsOf(
                productA,
                productB,
                product())

        // THEN
        val expectedLeft = termsOf(productA)
        val expectedRight = termsOf(productB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun product(): Product = mock()

    private fun anything(): Term = mock()

    private fun extract(operands: Terms) = ProductAndAnythingExtractor.extract(operands)
}