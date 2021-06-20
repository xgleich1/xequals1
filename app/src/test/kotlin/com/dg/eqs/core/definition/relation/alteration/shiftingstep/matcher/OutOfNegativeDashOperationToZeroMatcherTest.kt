package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.*
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfNegativeDashOperationToZeroMatcherTest {
    @Test
    fun `A out of negative dash operation to zero matcher matches with a shifting out of a negative dash operation to a positive zero`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = positiveZero()

        val source = termsOf(sourceSide.first)

        // THEN
        assertTrue(OutOfNegativeDashOperationToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to zero matcher matches with a shifting out of a negative dash operation to a negative zero`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = negativeZero()

        val source = termsOf(sourceSide.first)

        // THEN
        assertTrue(OutOfNegativeDashOperationToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to zero matcher doesn't match with a shifting out of anything other than a negative dash operation`() {
        // GIVEN
        val sourceSide = operation(term(), term())
        val targetSide = zero()

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfNegativeDashOperationToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to zero matcher doesn't match with a shifting to a term other than a zero`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfNegativeDashOperationToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to zero matcher doesn't match with a shifting to a value other than a zero`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = one()

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfNegativeDashOperationToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to zero matcher doesn't match when the shifted source isn't part of the negative dash operation determined by identity`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = zero()

        val source = termsOf(term())

        // THEN
        assertFalse(OutOfNegativeDashOperationToZeroMatcher.matches(sourceSide, targetSide, source))
    }
}