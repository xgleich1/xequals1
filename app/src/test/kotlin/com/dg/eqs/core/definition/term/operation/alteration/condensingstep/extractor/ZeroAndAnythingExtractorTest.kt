package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ZeroAndAnythingExtractorTest {
    @Test
    fun `Should extract the zero as the left & its successor as the right side from operands containing a zero followed by anything`() {
        // GIVEN
        val zero = zero()
        val anything = anything()
        val operands = termsOf(
                zero,
                anything,
                anything())

        // THEN
        val expectedLeft = termsOf(zero)
        val expectedRight = termsOf(anything)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first zero as the left & the second as the right side from operands containing a zero followed by a zero`() {
        // GIVEN
        val zeroA = zero()
        val zeroB = zero()
        val operands = termsOf(
                anything(),
                zeroA,
                zeroB)

        // THEN
        val expectedLeft = termsOf(zeroA)
        val expectedRight = termsOf(zeroB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first zero as the left & the second as the right side from operands containing exclusively zeros`() {
        // GIVEN
        val zeroA = zero()
        val zeroB = zero()
        val operands = termsOf(
                zeroA,
                zeroB,
                zero())

        // THEN
        val expectedLeft = termsOf(zeroA)
        val expectedRight = termsOf(zeroB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun zero(): Value = mock { on { isZero } doReturn true }

    private fun anything(): Term = mock()

    private fun extract(operands: Terms) = ZeroAndAnythingExtractor.extract(operands)
}