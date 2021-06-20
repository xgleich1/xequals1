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


class AnythingAndOneExtractorTest {
    @Test
    fun `Should extract the one as the right & its predecessor as the left side from operands containing anything before a one`() {
        // GIVEN
        val anything = anything()
        val one = one()
        val operands = termsOf(
                anything(),
                anything,
                one)

        // THEN
        val expectedLeft = termsOf(anything)
        val expectedRight = termsOf(one)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last one as the right & the second last as the left side from operands containing a one before a one`() {
        // GIVEN
        val oneA = one()
        val oneB = one()
        val operands = termsOf(
                oneA,
                oneB,
                anything())

        // THEN
        val expectedLeft = termsOf(oneA)
        val expectedRight = termsOf(oneB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last one as the right & the second last as the left side from operands containing exclusively ones`() {
        // GIVEN
        val oneB = one()
        val oneC = one()
        val operands = termsOf(
                one(),
                oneB,
                oneC)

        // THEN
        val expectedLeft = termsOf(oneB)
        val expectedRight = termsOf(oneC)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun one(): Value = mock { on { isOne } doReturn true }

    private fun extract(operands: Terms) = AnythingAndOneExtractor.extract(operands)
}