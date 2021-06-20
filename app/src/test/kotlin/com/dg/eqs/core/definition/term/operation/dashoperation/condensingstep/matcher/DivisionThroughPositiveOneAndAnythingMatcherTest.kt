package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DivisionThroughPositiveOneAndAnythingMatcherTest {
    @Test
    fun `A division through positive one and anything matcher matches with operands containing a division through positive one followed by anything`() {
        // GIVEN
        val operands = termsOf(
                divisionThroughPositiveOne(),
                anything(),
                anything())

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division through positive one and anything matcher matches with operands containing a division through positive one followed by a division`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                divisionThroughPositiveOne(),
                division())

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division through positive one and anything matcher matches with operands containing a division through positive one followed by a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                divisionThroughPositiveOne(),
                divisionThroughPositiveOne())

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division through positive one and anything matcher matches with operands containing exclusively divisions through positive one`() {
        // GIVEN
        val operands = termsOf(
                divisionThroughPositiveOne(),
                divisionThroughPositiveOne(),
                divisionThroughPositiveOne())

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division through positive one and anything matcher doesn't match with operands containing zero divisions through positive one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(),
                anything(),
                division(),
                anything())

        // THEN
        assertFalse(DivisionThroughPositiveOneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division through positive one and anything matcher doesn't match with operands containing a division through positive one not followed by anything`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                divisionThroughPositiveOne())

        // THEN
        assertFalse(DivisionThroughPositiveOneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A division through positive one and anything matcher matches with a left side consisting of a division through positive one and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(divisionThroughPositiveOne())
        val right = termsOf(anything())

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division through positive one and anything matcher matches with a left side consisting of a division through positive one and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(divisionThroughPositiveOne())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(DivisionThroughPositiveOneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division through positive one and anything matcher doesn't match with a left side consisting of several divisions through positive one`() {
        // GIVEN
        val left = termsOf(divisionThroughPositiveOne(), divisionThroughPositiveOne())
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionThroughPositiveOneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division through positive one and anything matcher doesn't match with a left side consisting of a single term other than a division through positive one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionThroughPositiveOneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A division through positive one and anything matcher doesn't match with a left side consisting of a single division other than a division through positive one`() {
        // GIVEN
        val left = termsOf(division())
        val right = termsOf(anything())

        // THEN
        assertFalse(DivisionThroughPositiveOneAndAnythingMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()

    private fun divisionThroughPositiveOne(): Division = mock { on { isDivisionThroughPositiveOne } doReturn true }
}