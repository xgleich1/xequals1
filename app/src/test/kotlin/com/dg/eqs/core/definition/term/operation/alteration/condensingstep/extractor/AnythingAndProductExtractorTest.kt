package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndProductExtractorTest {
    @Test
    fun `Should extract the product as the right & its predecessor as the left side from operands containing anything before a product`() {
        // GIVEN
        val anything = anything()
        val product = product()
        val operands = termsOf(
                anything(),
                anything,
                product)

        // THEN
        val expectedLeft = termsOf(anything)
        val expectedRight = termsOf(product)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last product as the right & the second last as the left side from operands containing a product before a product`() {
        // GIVEN
        val productA = product()
        val productB = product()
        val operands = termsOf(
                productA,
                productB,
                anything())

        // THEN
        val expectedLeft = termsOf(productA)
        val expectedRight = termsOf(productB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last product as the right & the second last as the left side from operands containing exclusively products`() {
        // GIVEN
        val productB = product()
        val productC = product()
        val operands = termsOf(
                product(),
                productB,
                productC)

        // THEN
        val expectedLeft = termsOf(productB)
        val expectedRight = termsOf(productC)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun product(): Product = mock()

    private fun extract(operands: Terms) = AnythingAndProductExtractor.extract(operands)
}