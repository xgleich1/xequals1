package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TermAndEqualTermMatcherTest {
    @Test
    fun `A term and equal term matcher matches with operands containing two equal terms`() {
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
        assertTrue(TermAndEqualTermMatcher.matches(operands))
    }

    @Test
    fun `A term and equal term matcher matches with operands containing more than two equal terms`() {
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
        assertTrue(TermAndEqualTermMatcher.matches(operands))
    }

    @Test
    fun `A term and equal term matcher matches with operands containing different kinds of equal terms`() {
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
        assertTrue(TermAndEqualTermMatcher.matches(operands))
    }

    @Test
    fun `A term and equal term matcher doesn't match with operands containing zero equal terms`() {
        // GIVEN
        val operands = termsOf(
                mock(),
                mock(),
                mock())

        // THEN
        assertFalse(TermAndEqualTermMatcher.matches(operands))
    }

    @Test
    fun `A term and equal term matcher matches with a left side consisting of a single term and a right side consisting of its equal`() {
        // GIVEN
        val firstEqualTerm = termA()
        val secondEqualTerm = termA()

        val left = termsOf(firstEqualTerm)
        val right = termsOf(secondEqualTerm)

        // THEN
        assertTrue(TermAndEqualTermMatcher.matches(left, right))
    }

    @Test
    fun `A term and equal term matcher doesn't match with a left side consisting of more than one term and a right side consisting of their equal`() {
        // GIVEN
        val firstEqualTerm = termA()
        val secondEqualTerm = termA()
        val thirdEqualTerm = termA()

        val left = termsOf(firstEqualTerm, secondEqualTerm)
        val right = termsOf(thirdEqualTerm)

        // THEN
        assertFalse(TermAndEqualTermMatcher.matches(left, right))
    }

    @Test
    fun `A term and equal term matcher doesn't match with a right side consisting of more than one term and a left side consisting of their equal`() {
        // GIVEN
        val firstEqualTerm = termA()
        val secondEqualTerm = termA()
        val thirdEqualTerm = termA()

        val left = termsOf(firstEqualTerm)
        val right = termsOf(secondEqualTerm, thirdEqualTerm)

        // THEN
        assertFalse(TermAndEqualTermMatcher.matches(left, right))
    }

    @Test
    fun `A term and equal term matcher doesn't match with a right side consisting of a single term and a left side not consisting of its equal`() {
        // GIVEN
        val left = termsOf(termA())
        val right = termsOf(termB())

        // THEN
        assertFalse(TermAndEqualTermMatcher.matches(left, right))
    }

    private fun termA() = positiveTerm()

    private fun termB() = negativeTerm()
}