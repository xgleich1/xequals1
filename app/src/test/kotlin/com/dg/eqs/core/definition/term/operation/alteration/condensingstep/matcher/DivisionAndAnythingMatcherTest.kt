package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionAndAnythingMatcherTest {
    @Test
    fun `A division and anything matcher matches with operands containing a division followed by anything`() {
        // GIVEN
        val operands = termsOf(
                division(),
                anything(),
                anything())

        // THEN
        assertTrue(DivisionAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division and anything matcher matches with operands containing a division followed by a division`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(),
                division())

        // THEN
        assertTrue(DivisionAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division and anything matcher matches with operands containing exclusively divisions`() {
        // GIVEN
        val operands = termsOf(
                division(),
                division(),
                division())

        // THEN
        assertTrue(DivisionAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division and anything matcher doesn't match with operands containing zero divisions`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(DivisionAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division and anything matcher doesn't match with operands containing a division not followed by anything`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                division())

        // THEN
        assertFalse(DivisionAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division and anything matcher matches with a left side consisting of a single division and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(division())
        val right = termsOf(anything())

        // THEN
        assertTrue(DivisionAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division and anything matcher matches with a left side consisting of a single division and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(division())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(DivisionAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division and anything matcher doesn't match with a left side consisting of several divisions`() {
        // GIVEN
        val left = termsOf(division(), division())
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division and anything matcher doesn't match with a left side consisting of a single term other than a division`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionAndAnythingMatcher.matches(left, right))
    }

    private fun division(): Division = mock()

    private fun anything(): Term = mock()
}