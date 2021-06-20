package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndAnythingMapperTest {
    @Test
    fun `Should map the left side to its division and leave the right side unmapped`() {
        // GIVEN
        val division = division()

        val left = termsOf(division)
        val right: Terms = mock()

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(division)
        assertThat(mappedRight).isEqualTo(right)
    }

    private fun division(): Division = mock()

    private fun map(left: Terms, right: Terms) =
            DivisionAndAnythingMapper.map(left, right)
}