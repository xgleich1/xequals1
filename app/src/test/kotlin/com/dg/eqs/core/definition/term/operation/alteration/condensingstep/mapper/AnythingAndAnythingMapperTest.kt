package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndAnythingMapperTest {
    @Test
    fun `Should leave the left and right side unmapped`() {
        // GIVEN
        val left: Terms = mock()
        val right: Terms = mock()

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(left)
        assertThat(mappedRight).isEqualTo(right)
    }

    private fun map(left: Terms, right: Terms) =
            AnythingAndAnythingMapper.map(left, right)
}