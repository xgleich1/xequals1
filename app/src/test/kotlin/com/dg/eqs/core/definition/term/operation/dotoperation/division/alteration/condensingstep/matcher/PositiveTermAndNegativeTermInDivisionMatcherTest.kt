package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class PositiveTermAndNegativeTermInDivisionMatcherTest {
    @Test
    fun `A positive term and negative term in division matcher matches with a positive numerator and a negative denominator`() {
        // GIVEN
        val operands = termsOf(
                positiveTerm(),
                negativeTerm())

        // THEN
        assertTrue(PositiveTermAndNegativeTermInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A positive term and negative term in division matcher doesn't match with a negative numerator and a negative denominator`() {
        // GIVEN
        val operands = termsOf(
                negativeTerm(),
                negativeTerm())

        // THEN
        assertFalse(PositiveTermAndNegativeTermInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A positive term and negative term in division matcher doesn't match with a positive numerator and a positive denominator`() {
        // GIVEN
        val operands = termsOf(
                positiveTerm(),
                positiveTerm())

        // THEN
        assertFalse(PositiveTermAndNegativeTermInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A positive term and negative term in division matcher doesn't match with a negative numerator and a positive denominator`() {
        // GIVEN
        val operands = termsOf(
                negativeTerm(),
                positiveTerm())

        // THEN
        assertFalse(PositiveTermAndNegativeTermInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A positive term and negative term in division matcher matches with a selection of a positive numerator and a negative denominator`() {
        // GIVEN
        val numerator = termsOf(positiveTerm())
        val denominator = termsOf(negativeTerm())

        // THEN
        assertTrue(PositiveTermAndNegativeTermInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A positive term and negative term in division matcher doesn't match with a selection of a negative numerator and a negative denominator`() {
        // GIVEN
        val numerator = termsOf(negativeTerm())
        val denominator = termsOf(negativeTerm())

        // THEN
        assertFalse(PositiveTermAndNegativeTermInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A positive term and negative term in division matcher doesn't match with a selection of a positive numerator and a positive denominator`() {
        // GIVEN
        val numerator = termsOf(positiveTerm())
        val denominator = termsOf(positiveTerm())

        // THEN
        assertFalse(PositiveTermAndNegativeTermInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A positive term and negative term in division matcher doesn't match with a selection of a negative numerator and a positive denominator`() {
        // GIVEN
        val numerator = termsOf(negativeTerm())
        val denominator = termsOf(positiveTerm())

        // THEN
        assertFalse(PositiveTermAndNegativeTermInDivisionMatcher.matches(numerator, denominator))
    }

    private fun positiveTerm(): Term = mock { on { isPositive } doReturn true }

    private fun negativeTerm(): Term = mock { on { isNegative } doReturn true }
}