package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductAndProductMapperTest {
    @Test
    fun `Should map the left and right side to their products`() {
        // GIVEN
        val leftProduct = product()
        val rightProduct = product()

        val left = termsOf(leftProduct)
        val right = termsOf(rightProduct)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftProduct)
        assertThat(mappedRight).isEqualTo(rightProduct)
    }

    private fun product(): Product = mock()

    private fun map(left: Terms, right: Terms) =
            ProductAndProductMapper.map(left, right)
}