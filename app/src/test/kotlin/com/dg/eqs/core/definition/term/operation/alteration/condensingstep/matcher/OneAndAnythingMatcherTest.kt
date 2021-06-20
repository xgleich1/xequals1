package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class OneAndAnythingMatcherTest {
    @Test
    fun `A one and anything matcher matches with operands containing a one followed by anything`() {
        // GIVEN
        val operands = termsOf(
                one(),
                anything(),
                anything())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A one and anything matcher matches with operands containing a one followed by a one`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                one(),
                one())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A one and anything matcher matches with operands containing exclusively ones`() {
        // GIVEN
        val operands = termsOf(
                one(),
                one(),
                one())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A one and anything matcher doesn't match with operands containing no ones at all`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A one and anything matcher doesn't match with operands containing a value other than one followed by anything`() {
        // GIVEN
        val operands = termsOf(
                value(),
                anything(),
                anything())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A one and anything matcher doesn't match with operands containing a one not followed by anything`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                one())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A one and anything matcher matches with a left side consisting of a single one and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(one())
        val right = termsOf(anything())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher matches with a left side consisting of a single one and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(one())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher matches with a left side consisting of several ones and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(one(), one())
        val right = termsOf(anything())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher matches with a left side consisting of several ones and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(one(), one())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher doesn't match with a left side consisting of a single term other than a one`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher doesn't match with a left side consisting of a value other than one`() {
        // GIVEN
        val left = termsOf(value())
        val right = termsOf(anything())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher doesn't match with a left side consisting of several terms but not only ones`() {
        // GIVEN
        val left = termsOf(one(), anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A one and anything matcher doesn't match with a left side consisting of several values but not only ones`() {
        // GIVEN
        val left = termsOf(one(), value())
        val right = termsOf(anything())

        // THEN
        assertFalse(OneAndAnythingMatcher.matches(left, right))
    }

    private fun one(): Value = mock { on { isOne } doReturn true }

    private fun value(): Value = mock()

    private fun anything(): Term = mock()
}