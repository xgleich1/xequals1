package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndTermMapperTest {
    @Test
    fun `Should map the left side to its division and the right side to its term`() {
        // GIVEN
        val leftDivision = division()
        val rightTerm = term()

        val left = termsOf(leftDivision)
        val right = termsOf(rightTerm)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftDivision)
        assertThat(mappedRight).isEqualTo(rightTerm)
    }

    private fun division(): Division = mock()

    private fun term(): Term = mock()

    private fun map(left: Terms, right: Terms) =
            DivisionAndTermMapper.map(left, right)
}