package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.mapper

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ValueAndValueMapperTest {
    @Test
    fun `Should map the left and right side to their values`() {
        // GIVEN
        val leftValue = value()
        val rightValue = value()

        val left = termsOf(leftValue)
        val right = termsOf(rightValue)

        // WHEN
        val (mappedLeft, mappedRight) = map(left, right)

        // THEN
        assertThat(mappedLeft).isEqualTo(leftValue)
        assertThat(mappedRight).isEqualTo(rightValue)
    }

    private fun value(): Value = mock()

    private fun map(left: Terms, right: Terms) =
            ValueAndValueMapper.map(left, right)
}