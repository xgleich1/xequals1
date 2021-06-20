package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndDivisionMapperTest {
    @Test
    fun `Should map the left side to its term and the right side to its division`() {
        // GIVEN
        val leftTerm = term()
        val rightDivision = division()

        val left = termsOf(leftTerm)
        val right = termsOf(rightDivision)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftTerm)
        assertThat(mappedRight).isEqualTo(rightDivision)
    }

    private fun term(): Term = mock()

    private fun division(): Division = mock()

    private fun map(left: Terms, right: Terms) =
            TermAndDivisionMapper.map(left, right)
}