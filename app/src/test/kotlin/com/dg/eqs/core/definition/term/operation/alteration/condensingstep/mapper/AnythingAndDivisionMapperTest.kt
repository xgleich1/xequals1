package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDivisionMapperTest {
    @Test
    fun `Should leave the left side unmapped and map the right side to its division`() {
        // GIVEN
        val division = division()

        val left: Terms = mock()
        val right = termsOf(division)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(left)
        assertThat(mappedRight).isEqualTo(division)
    }

    private fun division(): Division = mock()

    private fun map(left: Terms, right: Terms) =
            AnythingAndDivisionMapper.map(left, right)
}