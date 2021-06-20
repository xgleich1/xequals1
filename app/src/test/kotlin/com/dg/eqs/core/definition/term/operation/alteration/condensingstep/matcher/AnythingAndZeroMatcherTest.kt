package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndZeroMatcherTest {
    @Test
    fun `A anything and zero matcher matches with operands containing anything before a zero`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                zero())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(operands))
    }

    @Test
    fun `A anything and zero matcher matches with operands containing a zero before a zero`() {
        // GIVEN
        val operands = termsOf(
                zero(),
                zero(),
                anything())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(operands))
    }

    @Test
    fun `A anything and zero matcher matches with operands containing exclusively zeros`() {
        // GIVEN
        val operands = termsOf(
                zero(),
                zero(),
                zero())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(operands))
    }

    @Test
    fun `A anything and zero matcher doesn't match with operands containing no zeros at all`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(operands))
    }

    @Test
    fun `A anything and zero matcher doesn't match with operands containing anything followed by a value other than zero`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                value())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(operands))
    }

    @Test
    fun `A anything and zero matcher doesn't match with operands containing anything not followed by a zero`() {
        // GIVEN
        val operands = termsOf(
                zero(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(operands))
    }

    @Test
    fun `A anything and zero matcher matches with a left side consisting of a single term and a right side consisting of a single zero`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher matches with a left side consisting of several terms and a right side consisting of a single zero`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(zero())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher matches with a left side consisting of a single term and a right side consisting of several zeros`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero(), zero())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher matches with a left side consisting of several terms and a right side consisting of several zeros`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(zero(), zero())

        // THEN
        assertTrue(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher doesn't match with a right side consisting of a single term other than a zero`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher doesn't match with a right side consisting of a value other than zero`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(value())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher doesn't match with a right side consisting of several terms but not only zeros`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero(), anything())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(left, right))
    }

    @Test
    fun `A anything and zero matcher doesn't match with a right side consisting of several values but not only zeros`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(zero(), value())

        // THEN
        assertFalse(AnythingAndZeroMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun zero(): Value = mock { on { isZero } doReturn true }

    private fun value(): Value = mock()
}