package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ValueAndValueMatcherTest {
    @Test
    fun `A value and value matcher matches with operands containing exactly two values`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                value(),
                anything(),
                value(),
                anything())

        // THEN
        assertTrue(ValueAndValueMatcher.matches(operands))
    }

    @Test
    fun `A value and value matcher matches with operands containing more than two values`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                value(),
                anything(),
                value(),
                anything(),
                value(),
                anything())

        // THEN
        assertTrue(ValueAndValueMatcher.matches(operands))
    }

    @Test
    fun `A value and value matcher doesn't match with operands containing a single value`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                value(),
                anything())

        // THEN
        assertFalse(ValueAndValueMatcher.matches(operands))
    }

    @Test
    fun `A value and value matcher doesn't match with operands containing zero values`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(ValueAndValueMatcher.matches(operands))
    }

    @Test
    fun `A value and value matcher matches with a left & right side consisting of single values`() {
        // GIVEN
        val left = termsOf(value())
        val right = termsOf(value())

        // THEN
        assertTrue(ValueAndValueMatcher.matches(left, right))
    }

    @Test
    fun `A value and value matcher doesn't match with a left side consisting of more than one value`() {
        // GIVEN
        val left = termsOf(value(), value())
        val right = termsOf(value())

        // THEN
        assertFalse(ValueAndValueMatcher.matches(left, right))
    }

    @Test
    fun `A value and value matcher doesn't match with a right side consisting of more than one value`() {
        // GIVEN
        val left = termsOf(value())
        val right = termsOf(value(), value())

        // THEN
        assertFalse(ValueAndValueMatcher.matches(left, right))
    }

    @Test
    fun `A value and value matcher doesn't match with only a right side consisting of a single value`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(value())

        // THEN
        assertFalse(ValueAndValueMatcher.matches(left, right))
    }

    @Test
    fun `A value and value matcher doesn't match with only a left side consisting of a single value`() {
        // GIVEN
        val left = termsOf(value())
        val right = termsOf(anything())

        // THEN
        assertFalse(ValueAndValueMatcher.matches(left, right))
    }

    private fun value(): Value = mock()

    private fun anything(): Term = mock()
}