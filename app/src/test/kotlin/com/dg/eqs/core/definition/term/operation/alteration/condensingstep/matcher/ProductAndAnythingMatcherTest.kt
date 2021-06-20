package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ProductAndAnythingMatcherTest {
    @Test
    fun `A product and anything matcher matches with operands containing a product followed by anything`() {
        // GIVEN
        val operands = termsOf(
                product(),
                anything(),
                anything())

        // THEN
        assertTrue(ProductAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A product and anything matcher matches with operands containing a product followed by a product`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                product(),
                product())

        // THEN
        assertTrue(ProductAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A product and anything matcher matches with operands containing exclusively products`() {
        // GIVEN
        val operands = termsOf(
                product(),
                product(),
                product())

        // THEN
        assertTrue(ProductAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A product and anything matcher doesn't match with operands containing zero products`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(ProductAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A product and anything matcher doesn't match with operands containing a product not followed by anything`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                product())

        // THEN
        assertFalse(ProductAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A product and anything matcher matches with a left side consisting of a single product and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(product())
        val right = termsOf(anything())

        // THEN
        assertTrue(ProductAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A product and anything matcher matches with a left side consisting of a single product and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(product())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(ProductAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A product and anything matcher doesn't match with a left side consisting of several products`() {
        // GIVEN
        val left = termsOf(product(), product())
        val right = termsOf(anything())

        // THEN
        assertFalse(ProductAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A product and anything matcher doesn't match with a left side consisting of a single term other than a product`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(ProductAndAnythingMatcher.matches(left, right))
    }

    private fun product(): Product = mock()

    private fun anything(): Term = mock()
}