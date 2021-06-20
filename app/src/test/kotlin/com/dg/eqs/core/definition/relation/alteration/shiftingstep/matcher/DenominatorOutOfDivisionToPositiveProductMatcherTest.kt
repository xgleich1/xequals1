package com.dg.eqs.core.definition.relation.alteration.shiftingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.classes.division
import com.dg.eqs.classes.operation
import com.dg.eqs.classes.term
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DenominatorOutOfDivisionToPositiveProductMatcherTest {
    @Test
    fun `A denominator out of division to positive product matcher matches with a shifting of the denominator out of a division to a positive product`() {
        // GIVEN
        val sourceSide = division(term(), term())
        val targetSide = PositiveProduct(term(), term())

        val source = termsOf(sourceSide.denominator)

        // THEN
        assertTrue(DenominatorOutOfDivisionToPositiveProductMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A denominator out of division to positive product matcher doesn't match with a shifting out of anything other than a division`() {
        // GIVEN
        val sourceSide = operation(term(), term())
        val targetSide = PositiveProduct(term(), term())

        val source = termsOf(sourceSide.second)

        // THEN
        assertFalse(DenominatorOutOfDivisionToPositiveProductMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A denominator out of division to positive product matcher doesn't match with a shifting to anything other than a positive product`() {
        // GIVEN
        val sourceSide = division(term(), term())
        val targetSide = term()

        val source = termsOf(sourceSide.denominator)

        // THEN
        assertFalse(DenominatorOutOfDivisionToPositiveProductMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A denominator out of division to positive product matcher doesn't match when the shifted source is the divisions numerator determined by identity`() {
        // GIVEN
        val sourceSide = division(term(), term())
        val targetSide = PositiveProduct(term(), term())

        val source = termsOf(sourceSide.numerator)

        // THEN
        assertFalse(DenominatorOutOfDivisionToPositiveProductMatcher.matches(sourceSide, targetSide, source))
    }

    @Test
    fun `A denominator out of division to positive product matcher doesn't match when the shifted source isn't part of the division determined by identity`() {
        // GIVEN
        val sourceSide = division(term(), term())
        val targetSide = PositiveProduct(term(), term())

        val source = termsOf(term())

        // THEN
        assertFalse(DenominatorOutOfDivisionToPositiveProductMatcher.matches(sourceSide, targetSide, source))
    }
}