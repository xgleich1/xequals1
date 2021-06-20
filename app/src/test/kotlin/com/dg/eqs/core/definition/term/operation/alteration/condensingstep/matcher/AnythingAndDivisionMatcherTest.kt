package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndDivisionMatcherTest {
    @Test
    fun `A anything and division matcher matches with operands containing anything before a division`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                division())

        // THEN
        assertTrue(AnythingAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A anything and division matcher matches with operands containing a division before a division`() {
        // GIVEN
        val operands = termsOf(
                division(),
                division(),
                anything())

        // THEN
        assertTrue(AnythingAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A anything and division matcher matches with operands containing exclusively divisions`() {
        // GIVEN
        val operands = termsOf(
                division(),
                division(),
                division())

        // THEN
        assertTrue(AnythingAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A anything and division matcher doesn't match with operands containing zero divisions`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A anything and division matcher doesn't match with operands containing anything not followed by a division`() {
        // GIVEN
        val operands = termsOf(
                division(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndDivisionMatcher.matches(operands))
    }

    @Test
    fun `A anything and division matcher matches with a left side consisting of a single term and a right side consisting of a single division`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(division())

        // THEN
        assertTrue(AnythingAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division matcher matches with a left side consisting of several terms and a right side consisting of a single division`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(division())

        // THEN
        assertTrue(AnythingAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division matcher doesn't match with a right side consisting of several divisions`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(division(), division())

        // THEN
        assertFalse(AnythingAndDivisionMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division matcher doesn't match with a right side consisting of a single term other than a division`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(AnythingAndDivisionMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()
}