package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class EntireSideToPositiveDashOperationMatcherTest {
    @Test
    fun `An entire side to positive dash operation matcher matches with a shifting of the entire side to a positive dash operation`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = PositiveDashOperation(term(), term())

        val source = termsOf(sourceSide)

        // THEN
        assertTrue(EntireSideToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to positive dash operation matcher doesn't match with a shifting to anything other than a positive dash operation`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = term()

        val source = termsOf(sourceSide)

        // THEN
        assertFalse(EntireSideToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `An entire side to positive dash operation matcher doesn't match with a shifting of anything other than the entire side determined by identity`() {
        // GIVEN
        val sourceSide = term()
        val targetSide = PositiveDashOperation(term(), term())

        val source = termsOf(term())

        // THEN
        assertFalse(EntireSideToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }
}