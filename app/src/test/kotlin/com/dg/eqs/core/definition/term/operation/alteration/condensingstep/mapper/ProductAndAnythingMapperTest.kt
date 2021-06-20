package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ProductAndAnythingMapperTest {
    @Test
    fun `Should map the left side to its product and leave the right side unmapped`() {
        // GIVEN
        val product = product()

        val left = termsOf(product)
        val right: Terms = mock()

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(product)
        assertThat(mappedRight).isEqualTo(right)
    }

    private fun product(): Product = mock()

    private fun map(left: Terms, right: Terms) =
            ProductAndAnythingMapper.map(left, right)
}