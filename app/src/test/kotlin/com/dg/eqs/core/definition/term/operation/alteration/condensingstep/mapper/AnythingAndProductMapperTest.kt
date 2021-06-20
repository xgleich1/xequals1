package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndProductMapperTest {
    @Test
    fun `Should leave the left side unmapped and map the right side to its product`() {
        // GIVEN
        val product = product()

        val left: Terms = mock()
        val right = termsOf(product)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(left)
        assertThat(mappedRight).isEqualTo(product)
    }

    private fun product(): Product = mock()

    private fun map(left: Terms, right: Terms) =
            AnythingAndProductMapper.map(left, right)
}