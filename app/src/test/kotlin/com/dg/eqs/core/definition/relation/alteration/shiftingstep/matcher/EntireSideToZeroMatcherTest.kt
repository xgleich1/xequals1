package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.*
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class EntireSideToZeroMatcherTest {
    @Test
    fun `An entire side to zero matcher matches with a shifting of the entire side to a positive zero`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = positiveZero()

        val source = termsOf(sourceSide)

        // THEN
        assertTrue(EntireSideToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to zero matcher matches with a shifting of the entire side to a negative zero`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = negativeZero()

        val source = termsOf(sourceSide)

        // THEN
        assertTrue(EntireSideToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to zero matcher doesn't match with a shifting to a term other than a zero`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = term()

        val source = termsOf(sourceSide)

        // THEN
        assertFalse(EntireSideToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to zero matcher doesn't match with a shifting to a value other than a zero`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = one()

        val source = termsOf(sourceSide)

        // THEN
        assertFalse(EntireSideToZeroMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to zero matcher doesn't match with a shifting of anything other than the entire side determined by identity`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = zero()

        val source = termsOf(term())

        // THEN
        assertFalse(EntireSideToZeroMatcher.matches(sourceSide, targetSide, source))
    }
}