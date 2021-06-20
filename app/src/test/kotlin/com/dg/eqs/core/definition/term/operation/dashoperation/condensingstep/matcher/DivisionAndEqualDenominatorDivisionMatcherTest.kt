package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndEqualDenominatorDivisionMatcherTest {
    @Test
    fun `A division and equal denominator division matcher matches with operands containing exactly two divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        assertTrue(DivisionAndEqualDenominatorDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and equal denominator division matcher matches with operands containing more than two divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        assertTrue(DivisionAndEqualDenominatorDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and equal denominator division matcher matches with operands containing different pairs of divisions with equal denominators`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorB()),
                anything(),
                division(anything(), anything()),
                anything(),
                division(anything(), denominatorB()),
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        assertTrue(DivisionAndEqualDenominatorDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with operands containing a single division`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with operands containing zero divisions`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with operands containing divisions with different denominators`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(anything(), denominatorA()),
                anything(),
                division(anything(), denominatorB()),
                anything())

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and equal denominator division matcher matches with a left & right side consisting of single divisions with equal denominators`() {
        // GIVEN
        val left = termsOf(division(anything(), denominatorA()))
        val right = termsOf(division(anything(), denominatorA()))

        // THEN
        assertTrue(DivisionAndEqualDenominatorDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with a left side consisting of more than one division`() {
        // GIVEN
        val left = termsOf(division(anything(), denominatorA()), division(anything(), denominatorA()))
        val right = termsOf(division(anything(), denominatorA()))

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with a right side consisting of more than one division`() {
        // GIVEN
        val left = termsOf(division(anything(), denominatorA()))
        val right = termsOf(division(anything(), denominatorA()), division(anything(), denominatorA()))

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with only a right side consisting of a single division`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(division(anything(), anything()))

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with only a left side consisting of a single division`() {
        // GIVEN
        val left = termsOf(division(anything(), anything()))
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and equal denominator division matcher doesn't match with a left & right side consisting of single divisions with different denominators`() {
        // GIVEN
        val left = termsOf(division(anything(), denominatorA()))
        val right = termsOf(division(anything(), denominatorB()))

        // THEN
        assertFalse(DivisionAndEqualDenominatorDivisionMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun denominatorA() = positiveTerm()

    private fun denominatorB() = negativeTerm()

    private fun division(numerator: Term, denominator: Term): Division =
            mock {
                on { it.numerator } doReturn numerator
                on { it.denominator } doReturn denominator
            }
}