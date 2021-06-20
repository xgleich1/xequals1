package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.product
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfProductToAnythingMatcherTest {
    @Test
    fun `A out of product to anything matcher matches with a shifting out of a product to anything`() {
        // GIVEN
        val sourceSide = product(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.first)

        // THEN
        assertTrue(OutOfProductToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of product to anything matcher doesn't match with a shifting out of anything other than a product`() {
        // GIVEN
        val sourceSide = operation(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.first)

        // THEN
        assertFalse(OutOfProductToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of product to anything matcher doesn't match when the shifted source isn't part of the product determined by identity`() {
        // GIVEN
        val sourceSide = product(term(), term())
        val targetSide = term()

        val source = termsOf(term())

        // THEN
        assertFalse(OutOfProductToAnythingMatcher.matches(sourceSide, targetSide, source))
    }
}