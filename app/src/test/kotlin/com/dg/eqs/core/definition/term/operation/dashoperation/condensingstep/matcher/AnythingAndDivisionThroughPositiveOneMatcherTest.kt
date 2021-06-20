package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndDivisionThroughPositiveOneMatcherTest {
    @Test
    fun `A anything and division through positive one matcher matches with operands containing anything before a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                divisionThroughPositiveOne())

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and division through positive one matcher matches with operands containing a division before a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                division(),
                divisionThroughPositiveOne(),
                anything())

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and division through positive one matcher matches with operands containing a division through positive one before a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                divisionThroughPositiveOne(),
                divisionThroughPositiveOne(),
                anything())

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and division through positive one matcher matches with operands containing exclusively divisions through positive one`() {
        // GIVEN
        val operands = termsOf(
                divisionThroughPositiveOne(),
                divisionThroughPositiveOne(),
                divisionThroughPositiveOne())

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and division through positive one matcher doesn't match with operands containing zero divisions through positive one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                division(),
                anything(),
                division(),
                anything())

        // THEN
        assertFalse(AnythingAndDivisionThroughPositiveOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and division through positive one matcher doesn't match with operands containing anything not followed by a division through positive one`() {
        // GIVEN
        val operands = termsOf(
                divisionThroughPositiveOne(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndDivisionThroughPositiveOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and division through positive one matcher matches with a left side consisting of a single term and a right side consisting of a division through positive one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(divisionThroughPositiveOne())

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division through positive one matcher matches with a left side consisting of several terms and a right side consisting of a division through positive one`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(divisionThroughPositiveOne())

        // THEN
        assertTrue(AnythingAndDivisionThroughPositiveOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division through positive one matcher doesn't match with a right side consisting of several divisions through positive one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(divisionThroughPositiveOne(), divisionThroughPositiveOne())

        // THEN
        assertFalse(AnythingAndDivisionThroughPositiveOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division through positive one matcher doesn't match with a right side consisting of a single term other than a division through positive one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(AnythingAndDivisionThroughPositiveOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and division through positive one matcher doesn't match with a right side consisting of a single division other than a division through positive one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(division())

        // THEN
        assertFalse(AnythingAndDivisionThroughPositiveOneMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()

    private fun divisionThroughPositiveOne(): Division = mock { on { isDivisionThroughPositiveOne } doReturn true }
}