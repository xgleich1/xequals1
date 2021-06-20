package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.item.value.Value
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ValueAndValueWithGcdGreaterOneInDivisionMatcherTest {
    @Test
    fun `A value and value with gcd greater one in division matcher matches with a value as the numerator & a value as the denominator having an gcd greater one`() {
        // GIVEN
        val operands = termsOf(
                value(4),
                value(2))

        // THEN
        assertTrue(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with a value as the numerator & anything other than a value as the denominator`() {
        // GIVEN
        val operands = termsOf(
                value(4),
                anything())

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with anything other than a value as the numerator & a value as the denominator`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                value(2))

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with anything other than a value as the numerator and denominator`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything())

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with a value as the numerator & a value as the denominator having an gcd of one`() {
        // GIVEN
        val operands = termsOf(
                value(3),
                value(2))

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(operands))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher matches with a selection of a value as the numerator & a value as the denominator having an gcd greater one`() {
        // GIVEN
        val numerator = termsOf(value(4))
        val denominator = termsOf(value(2))

        // THEN
        assertTrue(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with a selection of a value as the numerator & anything other than a value as the denominator`() {
        // GIVEN
        val numerator = termsOf(value(4))
        val denominator = termsOf(anything())

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with a selection of anything other than a value as the numerator & a value as the denominator`() {
        // GIVEN
        val numerator = termsOf(anything())
        val denominator = termsOf(value(2))

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with a selection of anything other than a value as the numerator and denominator`() {
        // GIVEN
        val numerator = termsOf(anything())
        val denominator = termsOf(anything())

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(numerator, denominator))
    }

    @Test
    fun `A value and value with gcd greater one in division matcher doesn't match with a selection of a value as the numerator & a value as the denominator having an gcd of one`() {
        // GIVEN
        val numerator = termsOf(value(3))
        val denominator = termsOf(value(2))

        // THEN
        assertFalse(ValueAndValueWithGcdGreaterOneInDivisionMatcher.matches(numerator, denominator))
    }

    private fun value(unsignedNumber: Int): Value = mock { on { it.unsignedNumber } doReturn unsignedNumber }

    private fun anything(): Term = mock()
}