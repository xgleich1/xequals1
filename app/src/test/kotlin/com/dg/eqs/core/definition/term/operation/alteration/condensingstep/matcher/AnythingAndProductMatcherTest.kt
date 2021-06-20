package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndProductMatcherTest {
    @Test
    fun `A anything and product matcher matches with operands containing anything before a product`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                product())

        // THEN
        assertTrue(AnythingAndProductMatcher.matches(operands))
    }

    @Test
    fun `A anything and product matcher matches with operands containing a product before a product`() {
        // GIVEN
        val operands = termsOf(
                product(),
                product(),
                anything())

        // THEN
        assertTrue(AnythingAndProductMatcher.matches(operands))
    }

    @Test
    fun `A anything and product matcher matches with operands containing exclusively products`() {
        // GIVEN
        val operands = termsOf(
                product(),
                product(),
                product())

        // THEN
        assertTrue(AnythingAndProductMatcher.matches(operands))
    }

    @Test
    fun `A anything and product matcher doesn't match with operands containing zero products`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndProductMatcher.matches(operands))
    }

    @Test
    fun `A anything and product matcher doesn't match with operands containing anything not followed by a product`() {
        // GIVEN
        val operands = termsOf(
                product(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndProductMatcher.matches(operands))
    }

    @Test
    fun `A anything and product matcher matches with a left side consisting of a single term and a right side consisting of a single product`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(product())

        // THEN
        assertTrue(AnythingAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A anything and product matcher matches with a left side consisting of several terms and a right side consisting of a single product`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(product())

        // THEN
        assertTrue(AnythingAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A anything and product matcher doesn't match with a right side consisting of several products`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(product(), product())

        // THEN
        assertFalse(AnythingAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A anything and product matcher doesn't match with a right side consisting of a single term other than a product`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(AnythingAndProductMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun product(): Product = mock()
}