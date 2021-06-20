package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfPositiveDashOperationToAnythingMatcherTest {
    @Test
    fun `A out of positive dash operation to anything matcher matches with a shifting out of a positive dash operation to anything`() {
        // GIVEN
        val sourceSide = PositiveDashOperation(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.first)

        // THEN
        assertTrue(OutOfPositiveDashOperationToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of positive dash operation to anything matcher doesn't match with a shifting out of anything other than a positive dash operation`() {
        // GIVEN
        val sourceSide = operation(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfPositiveDashOperationToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of positive dash operation to anything matcher doesn't match when the shifted source isn't part of the positive dash operation determined by identity`() {
        // GIVEN
        val sourceSide = PositiveDashOperation(term(), term())
        val targetSide = term()

        val source = termsOf(term())

        // THEN
        assertFalse(OutOfPositiveDashOperationToAnythingMatcher.matches(sourceSide, targetSide, source))
    }
}