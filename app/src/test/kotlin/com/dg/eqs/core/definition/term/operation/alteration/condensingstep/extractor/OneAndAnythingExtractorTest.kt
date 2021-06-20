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


class OneAndAnythingExtractorTest {
    @Test
    fun `Should extract the one as the left & its successor as the right side from operands containing a one followed by anything`() {
        // GIVEN
        val one = one()
        val anything = anything()
        val operands = termsOf(
                one,
                anything,
                anything())

        // THEN
        val expectedLeft = termsOf(one)
        val expectedRight = termsOf(anything)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first one as the left & the second as the right side from operands containing a one followed by a one`() {
        // GIVEN
        val oneA = one()
        val oneB = one()
        val operands = termsOf(
                anything(),
                oneA,
                oneB)

        // THEN
        val expectedLeft = termsOf(oneA)
        val expectedRight = termsOf(oneB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first one as the left & the second as the right side from operands containing exclusively ones`() {
        // GIVEN
        val oneA = one()
        val oneB = one()
        val operands = termsOf(
                oneA,
                oneB,
                one())

        // THEN
        val expectedLeft = termsOf(oneA)
        val expectedRight = termsOf(oneB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun one(): Value = mock { on { isOne } doReturn true }

    private fun anything(): Term = mock()

    private fun extract(operands: Terms) = OneAndAnythingExtractor.extract(operands)
}