package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ProductAndProductMatcherTest {
    @Test
    fun `A product and product matcher matches with operands containing exactly two products`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                product(),
                anything(),
                product(),
                anything())

        // THEN
        assertTrue(ProductAndProductMatcher.matches(operands))
    }

    @Test
    fun `A product and product matcher matches with operands containing more than two products`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                product(),
                anything(),
                product(),
                anything(),
                product(),
                anything())

        // THEN
        assertTrue(ProductAndProductMatcher.matches(operands))
    }

    @Test
    fun `A product and product matcher doesn't match with operands containing a single product`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                product(),
                anything())

        // THEN
        assertFalse(ProductAndProductMatcher.matches(operands))
    }

    @Test
    fun `A product and product matcher doesn't match with operands containing zero products`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(ProductAndProductMatcher.matches(operands))
    }

    @Test
    fun `A product and product matcher matches with a left & right side consisting of single products`() {
        // GIVEN
        val left = termsOf(product())
        val right = termsOf(product())

        // THEN
        assertTrue(ProductAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A product and product matcher doesn't match with a left side consisting of more than one product`() {
        // GIVEN
        val left = termsOf(product(), product())
        val right = termsOf(product())

        // THEN
        assertFalse(ProductAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A product and product matcher doesn't match with a right side consisting of more than one product`() {
        // GIVEN
        val left = termsOf(product())
        val right = termsOf(product(), product())

        // THEN
        assertFalse(ProductAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A product and product matcher doesn't match with only a right side consisting of a single product`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(product())

        // THEN
        assertFalse(ProductAndProductMatcher.matches(left, right))
    }

    @Test
    fun `A product and product matcher doesn't match with only a left side consisting of a single product`() {
        // GIVEN
        val left = termsOf(product())
        val right = termsOf(anything())

        // THEN
        assertFalse(ProductAndProductMatcher.matches(left, right))
    }

    private fun product(): Product = mock()

    private fun anything(): Term = mock()
}