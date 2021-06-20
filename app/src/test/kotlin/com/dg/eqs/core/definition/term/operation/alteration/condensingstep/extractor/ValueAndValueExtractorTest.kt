package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ValueAndValueExtractorTest {
    @Test
    fun `Should extract the first value as the left & the second as the right side from operands containing exactly two values`() {
        // GIVEN
        val valueA = value()
        val valueB = value()
        val operands = termsOf(
                anything(),
                valueA,
                anything(),
                valueB,
                anything())

        // THEN
        val expectedLeft = termsOf(valueA)
        val expectedRight = termsOf(valueB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first value as the left & the second as the right side from operands containing more than two values`() {
        // GIVEN
        val valueA = value()
        val valueB = value()
        val operands = termsOf(
                anything(),
                valueA,
                anything(),
                valueB,
                anything(),
                value(),
                anything())

        // THEN
        val expectedLeft = termsOf(valueA)
        val expectedRight = termsOf(valueB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun value(): Value = mock()

    private fun anything(): Term = mock()

    private fun extract(operands: Terms) = ValueAndValueExtractor.extract(operands)
}