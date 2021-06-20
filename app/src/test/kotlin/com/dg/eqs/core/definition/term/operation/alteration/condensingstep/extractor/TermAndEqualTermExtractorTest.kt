package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndEqualTermExtractorTest {
    @Test
    fun `Should extract the first equal term as the left & the second as the right side from operands containing exactly two equal terms`() {
        // GIVEN
        val firstEqualTerm = termA()
        val secondEqualTerm = termA()

        val operands = termsOf(
                mock(),
                firstEqualTerm,
                mock(),
                secondEqualTerm,
                mock())

        // THEN
        val expectedLeft = termsOf(firstEqualTerm)
        val expectedRight = termsOf(secondEqualTerm)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first equal term as the left & the second as the right side from operands containing more than two equal terms`() {
        // GIVEN
        val firstEqualTerm = termA()
        val secondEqualTerm = termA()
        val thirdEqualTerm = termA()

        val operands = termsOf(
                mock(),
                firstEqualTerm,
                mock(),
                secondEqualTerm,
                mock(),
                thirdEqualTerm,
                mock())

        // THEN
        val expectedLeft = termsOf(firstEqualTerm)
        val expectedRight = termsOf(secondEqualTerm)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first kind of equal terms as the left & right side from operands containing different kinds of equal terms`() {
        // GIVEN
        val firstEqualTermA = termA()
        val secondEqualTermA = termA()
        val firstEqualTermB = termB()
        val secondEqualTermB = termB()

        val operands = termsOf(
                mock(),
                firstEqualTermA,
                mock(),
                secondEqualTermA,
                mock(),
                firstEqualTermB,
                mock(),
                secondEqualTermB,
                mock())

        // THEN
        val expectedLeft = termsOf(firstEqualTermA)
        val expectedRight = termsOf(secondEqualTermA)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun termA() = positiveTerm()

    private fun termB() = negativeTerm()

    private fun extract(operands: Terms) = TermAndEqualTermExtractor.extract(operands)
}