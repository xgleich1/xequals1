package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.classes.division
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.product
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class OutOfProductAsDenominatorOfDivisionToAnythingMatcherTest {
    @Test
    fun `A out of product as denominator of division to anything matcher matches with a shifting out of a product as the denominator of a division to anything`() {
        // GIVEN
        val denominator = product(term(), term())

        val sourceSide = division(term(), denominator)
        val targetSide = term()

        val source = termsOf(denominator.first)

        // THEN
        assertTrue(OutOfProductAsDenominatorOfDivisionToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of product as denominator of division to anything matcher doesn't match with a shifting out of a product as the operand of anything other than a division`() {
        // GIVEN
        val product = product(term(), term())

        val sourceSide = operation(term(), product)
        val targetSide = term()

        val source = termsOf(product.first)

        // THEN
        assertFalse(OutOfProductAsDenominatorOfDivisionToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of product as denominator of division to anything matcher doesn't match when the shifted source is out of the divisions numerator`() {
        // GIVEN
        val numerator = product(term(), term())

        val sourceSide = division(numerator, term())
        val targetSide = term()

        val source = termsOf(numerator.first)

        // THEN
        assertFalse(OutOfProductAsDenominatorOfDivisionToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of product as denominator of division to anything matcher doesn't match with a shifting out of anything other than a product as the divisions denominator`() {
        // GIVEN
        val denominator = operation(term(), term())

        val sourceSide = division(term(), denominator)
        val targetSide = term()

        val source = termsOf(denominator.first)

        // THEN
        assertFalse(OutOfProductAsDenominatorOfDivisionToAnythingMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A out of product as denominator of division to anything matcher doesn't match when the shifted source isn't part of the product determined by identity`() {
        // GIVEN
        val denominator = product(term(), term())

        val sourceSide = division(term(), denominator)
        val targetSide = term()

        val source = termsOf(term())

        // THEN
        assertFalse(OutOfProductAsDenominatorOfDivisionToAnythingMatcher.matches(sourceSide, targetSide, source))
    }
}