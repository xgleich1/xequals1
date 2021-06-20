package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndOppositeTermExtractorTest {
    @Test
    fun `Should extract the pair of a term and its opposite as the left & right side from operands containing exactly one`() {
        // GIVEN
        val (term, oppositeTerm) = termAndOpposite()

        val operands = termsOf(
                term(),
                term,
                term(),
                oppositeTerm,
                term())

        // THEN
        val expectedLeft = termsOf(term)
        val expectedRight = termsOf(oppositeTerm)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first pair of a term and its opposite as the left & right side from operands containing more than one`() {
        // GIVEN
        val (termA, oppositeTermA) = termAndOpposite()
        val (termB, oppositeTermB) = termAndOpposite()

        val operands = termsOf(
                term(),
                termA,
                term(),
                oppositeTermA,
                term(),
                termB,
                term(),
                oppositeTermB,
                term())

        // THEN
        val expectedLeft = termsOf(termA)
        val expectedRight = termsOf(oppositeTermA)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun term(): Term = mock()

    private fun termAndOpposite(): Pair<Term, Term> {
        val anything = term()
        val opposite = term()

        whenever(anything.invert())
                .thenReturn(opposite)

        whenever(opposite.invert())
                .thenReturn(anything)

        return Pair(anything, opposite)
    }

    private fun extract(operands: Terms) = TermAndOppositeTermExtractor.extract(operands)
}