package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndDivisionMatcherTest {
    @Test
    fun `A division and division matcher matches with operands containing exactly two divisions`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(),
                anything(),
                division(),
                anything())

        // THEN
        assertTrue(DivisionAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and division matcher matches with operands containing more than two divisions`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(),
                anything(),
                division(),
                anything(),
                division(),
                anything())

        // THEN
        assertTrue(DivisionAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and division matcher doesn't match with operands containing a single division`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(),
                anything())

        // THEN
        assertFalse(DivisionAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and division matcher doesn't match with operands containing zero divisions`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(DivisionAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A division and division matcher matches with a left & right side consisting of single divisions`() {
        // GIVEN
        val left = termsOf(division())
        val right = termsOf(division())

        // THEN
        assertTrue(DivisionAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and division matcher doesn't match with a left side consisting of more than one division`() {
        // GIVEN
        val left = termsOf(division(), division())
        val right = termsOf(division())

        // THEN
        assertFalse(DivisionAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and division matcher doesn't match with a right side consisting of more than one division`() {
        // GIVEN
        val left = termsOf(division())
        val right = termsOf(division(), division())

        // THEN
        assertFalse(DivisionAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and division matcher doesn't match with only a right side consisting of a single division`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(division())

        // THEN
        assertFalse(DivisionAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A division and division matcher doesn't match with only a left side consisting of a single division`() {
        // GIVEN
        val left = termsOf(division())
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionAndDivisionMatcher.matches(left, right))
    }

    private fun division(): Division = mock()

    private fun anything(): Term = mock()
}