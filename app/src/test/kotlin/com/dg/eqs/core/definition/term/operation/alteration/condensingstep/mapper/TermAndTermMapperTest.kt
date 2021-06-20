package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndTermMapperTest {
    @Test
    fun `Should map the left and right side to their terms`() {
        // GIVEN
        val leftTerm = term()
        val rightTerm = term()

        val left = termsOf(leftTerm)
        val right = termsOf(rightTerm)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftTerm)
        assertThat(mappedRight).isEqualTo(rightTerm)
    }

    private fun term(): Term = mock()

    private fun map(left: Terms, right: Terms) =
            TermAndTermMapper.map(left, right)
}