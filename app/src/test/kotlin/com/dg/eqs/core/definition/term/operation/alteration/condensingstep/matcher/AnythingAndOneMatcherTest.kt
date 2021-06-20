package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndOneMatcherTest {
    @Test
    fun `A anything and one matcher matches with operands containing anything before a one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                one())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and one matcher matches with operands containing a one before a one`() {
        // GIVEN
        val operands = termsOf(
                one(),
                one(),
                anything())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and one matcher matches with operands containing exclusively ones`() {
        // GIVEN
        val operands = termsOf(
                one(),
                one(),
                one())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and one matcher doesn't match with operands containing no ones at all`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and one matcher doesn't match with operands containing anything followed by a value other than one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                value())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and one matcher doesn't match with operands containing anything not followed by a one`() {
        // GIVEN
        val operands = termsOf(
                one(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(operands))
    }

    @Test
    fun `A anything and one matcher matches with a left side consisting of a single term and a right side consisting of a single one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(one())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher matches with a left side consisting of several terms and a right side consisting of a single one`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(one())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher matches with a left side consisting of a single term and a right side consisting of several ones`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(one(), one())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher matches with a left side consisting of several terms and a right side consisting of several ones`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(one(), one())

        // THEN
        assertTrue(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher doesn't match with a right side consisting of a single term other than a one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher doesn't match with a right side consisting of a value other than one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(value())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher doesn't match with a right side consisting of several terms but not only ones`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(one(), anything())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(left, right))
    }

    @Test
    fun `A anything and one matcher doesn't match with a right side consisting of several values but not only ones`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(one(), value())

        // THEN
        assertFalse(AnythingAndOneMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun one(): Value = mock { on { isOne } doReturn true }

    private fun value(): Value = mock()
}