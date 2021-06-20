package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ZeroAndAnythingMatcherTest {
    @Test
    fun `A zero and anything matcher matches with operands containing a zero followed by anything`() {
        // GIVEN
        val operands = termsOf(
                zero(),
                anything(),
                anything())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A zero and anything matcher matches with operands containing a zero followed by a zero`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                zero(),
                zero())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A zero and anything matcher matches with operands containing exclusively zeros`() {
        // GIVEN
        val operands = termsOf(
                zero(),
                zero(),
                zero())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A zero and anything matcher doesn't match with operands containing no zeros at all`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A zero and anything matcher doesn't match with operands containing a value other than zero followed by anything`() {
        // GIVEN
        val operands = termsOf(
                value(),
                anything(),
                anything())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A zero and anything matcher doesn't match with operands containing a zero not followed by anything`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                zero())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A zero and anything matcher matches with a left side consisting of a single zero and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(zero())
        val right = termsOf(anything())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher matches with a left side consisting of a single zero and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(zero())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher matches with a left side consisting of several zeros and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(zero(), zero())
        val right = termsOf(anything())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher matches with a left side consisting of several zeros and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(zero(), zero())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher doesn't match with a left side consisting of a single term other than a zero`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher doesn't match with a left side consisting of a value other than zero`() {
        // GIVEN
        val left = termsOf(value())
        val right = termsOf(anything())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher doesn't match with a left side consisting of several terms but not only zeros`() {
        // GIVEN
        val left = termsOf(zero(), anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A zero and anything matcher doesn't match with a left side consisting of several values but not only zeros`() {
        // GIVEN
        val left = termsOf(zero(), value())
        val right = termsOf(anything())

        // THEN
        assertFalse(ZeroAndAnythingMatcher.matches(left, right))
    }

    private fun zero(): Value = mock { on { isZero } doReturn true }

    private fun value(): Value = mock()

    private fun anything(): Term = mock()
}