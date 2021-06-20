package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndValueMapperTest {
    @Test
    fun `Should map the left side to its term and the right side to its value`() {
        // GIVEN
        val leftTerm = term()
        val rightValue = value()

        val left = termsOf(leftTerm)
        val right = termsOf(rightValue)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftTerm)
        assertThat(mappedRight).isEqualTo(rightValue)
    }

    private fun term(): Term = mock()

    private fun value(): Value = mock()

    private fun map(left: Terms, right: Terms) = TermAndValueMapper.map(left, right)
}