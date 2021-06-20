package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class EntireSideToAnythingMatcherTest {
    @Test
    fun `An entire side to anything matcher matches with a shifting of the entire side to anything`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = term()

        val source = termsOf(sourceSide)

        // THEN
        assertTrue(EntireSideToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to anything matcher doesn't match with a shifting of anything other than the entire side determined by identity`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = term()

        val source = termsOf(term())

        // THEN
        assertFalse(EntireSideToAnythingMatcher.matches(sourceSide, targetSide, source))
    }
}