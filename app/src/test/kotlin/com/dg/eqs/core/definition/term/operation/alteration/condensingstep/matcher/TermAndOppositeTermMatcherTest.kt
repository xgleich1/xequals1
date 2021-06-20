package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TermAndOppositeTermMatcherTest {
    @Test
    fun `A term and opposite term matcher matches with operands containing exactly one pair of a term and its opposite`() {
        // GIVEN
        val (term, oppositeTerm) = termAndOpposite()

        val operands = termsOf(
                term(),
                term,
                term(),
                oppositeTerm,
                term())

        // THEN
        assertTrue(TermAndOppositeTermMatcher.matches(operands))
    }

    @Test
    fun `A term and opposite term matcher matches with operands containing more than one pair of a term and its opposite`() {
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
        assertTrue(TermAndOppositeTermMatcher.matches(operands))
    }

    @Test
    fun `A term and opposite term matcher doesn't match with operands containing zero pairs of a term and its opposite`() {
        // GIVEN
        val operands = termsOf(
                term(),
                term(),
                term())

        // THEN
        assertFalse(TermAndOppositeTermMatcher.matches(operands))
    }

    @Test
    fun `A term and opposite term matcher matches with a left side consisting of a single term and a right side consisting of its opposite`() {
        // GIVEN
        val (term, oppositeTerm) = termAndOpposite()

        val left = termsOf(term)
        val right = termsOf(oppositeTerm)

        // THEN
        assertTrue(TermAndOppositeTermMatcher.matches(left, right))
    }

    @Test
    fun `A term and opposite term matcher doesn't match with a left side consisting of more than one term and a right side consisting of a opposite`() {
        // GIVEN
        val (term, oppositeTerm) = termAndOpposite()

        val left = termsOf(term, term())
        val right = termsOf(oppositeTerm)

        // THEN
        assertFalse(TermAndOppositeTermMatcher.matches(left, right))
    }

    @Test
    fun `A term and opposite term matcher doesn't match with a right side consisting of more than one term and a left side consisting of a opposite`() {
        // GIVEN
        val (term, oppositeTerm) = termAndOpposite()

        val left = termsOf(term)
        val right = termsOf(oppositeTerm, term())

        // THEN
        assertFalse(TermAndOppositeTermMatcher.matches(left, right))
    }

    @Test
    fun `A term and opposite term matcher doesn't match with a right side consisting of a single term and a left side not consisting of its opposite`() {
        // GIVEN
        val left = termsOf(term())
        val right = termsOf(term())

        // THEN
        assertFalse(TermAndOppositeTermMatcher.matches(left, right))
    }

    private fun term(): Term = mock()

    private fun termAndOpposite(): Pair<Term, Term> {
        val anything = term()
        val opposite = term()

        whenever(anything.invert())
                .thenReturn(opposite)

        whenever(opposite.invert())
                .thenReturn(anything)

        return anything and opposite
    }
}