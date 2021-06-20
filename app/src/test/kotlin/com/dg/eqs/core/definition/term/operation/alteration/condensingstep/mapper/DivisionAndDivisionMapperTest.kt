package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndDivisionMapperTest {
    @Test
    fun `Should map the left and right side to their divisions`() {
        // GIVEN
        val leftDivision = division()
        val rightDivision = division()

        val left = termsOf(leftDivision)
        val right = termsOf(rightDivision)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftDivision)
        assertThat(mappedRight).isEqualTo(rightDivision)
    }

    private fun division(): Division = mock()

    private fun map(left: Terms, right: Terms) =
            DivisionAndDivisionMapper.map(left, right)
}