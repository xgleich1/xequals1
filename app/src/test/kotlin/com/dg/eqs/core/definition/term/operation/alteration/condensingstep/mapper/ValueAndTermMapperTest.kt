package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ValueAndTermMapperTest {
    @Test
    fun `Should map the left side to its value and the right side to its term`() {
        // GIVEN
        val leftValue = value()
        val rightTerm = term()

        val left = termsOf(leftValue)
        val right = termsOf(rightTerm)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftValue)
        assertThat(mappedRight).isEqualTo(rightTerm)
    }

    private fun value(): Value = mock()

    private fun term(): Term = mock()

    private fun map(left: Terms, right: Terms) = ValueAndTermMapper.map(left, right)
}