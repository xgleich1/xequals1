package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.operation.dashoperation.NegativeDashOperation
import com.dg.eqs.core.definition.term.operation.dashoperation.PositiveDashOperation
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfNegativeDashOperationToPositiveDashOperationMatcherTest {
    @Test
    fun `A out of negative dash operation to positive dash operation matcher matches with a shifting out of a negative dash operation to a positive dash operation`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = PositiveDashOperation(term(), term())

        val source = termsOf(sourceSide.first)

        // THEN
        assertTrue(OutOfNegativeDashOperationToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to positive dash operation matcher doesn't match with a shifting out of anything other than a negative dash operation`() {
        // GIVEN
        val sourceSide = operation(term(), term())
        val targetSide = PositiveDashOperation(term(), term())

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfNegativeDashOperationToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to positive dash operation matcher doesn't match with a shifting to anything other than a positive dash operation`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfNegativeDashOperationToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of negative dash operation to positive dash operation matcher doesn't match when the shifted source isn't part of the negative dash operation determined by identity`() {
        // GIVEN
        val sourceSide = NegativeDashOperation(term(), term())
        val targetSide = PositiveDashOperation(term(), term())

        val source = termsOf(term())

        // THEN
        assertFalse(OutOfNegativeDashOperationToPositiveDashOperationMatcher.matches(sourceSide, targetSide, source))
    }
}