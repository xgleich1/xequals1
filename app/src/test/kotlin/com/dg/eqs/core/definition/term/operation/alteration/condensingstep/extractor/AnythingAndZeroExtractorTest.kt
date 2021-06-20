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


class AnythingAndZeroExtractorTest {
    @Test
    fun `Should extract the zero as the right & its predecessor as the left side from operands containing anything before a zero`() {
        // GIVEN
        val anything = anything()
        val zero = zero()
        val operands = termsOf(
                anything(),
                anything,
                zero)

        // THEN
        val expectedLeft = termsOf(anything)
        val expectedRight = termsOf(zero)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last zero as the right & the second last as the left side from operands containing a zero before a zero`() {
        // GIVEN
        val zeroA = zero()
        val zeroB = zero()
        val operands = termsOf(
                zeroA,
                zeroB,
                anything())

        // THEN
        val expectedLeft = termsOf(zeroA)
        val expectedRight = termsOf(zeroB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last zero as the right & the second last as the left side from operands containing exclusively zeros`() {
        // GIVEN
        val zeroB = zero()
        val zeroC = zero()
        val operands = termsOf(
                zero(),
                zeroB,
                zeroC)

        // THEN
        val expectedLeft = termsOf(zeroB)
        val expectedRight = termsOf(zeroC)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun zero(): Value = mock { on { isZero } doReturn true }

    private fun extract(operands: Terms) = AnythingAndZeroExtractor.extract(operands)
}