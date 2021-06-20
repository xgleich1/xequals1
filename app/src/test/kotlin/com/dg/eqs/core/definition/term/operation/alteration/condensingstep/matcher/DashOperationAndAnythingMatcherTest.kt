package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DashOperationAndAnythingMatcherTest {
    @Test
    fun `A dash operation and anything matcher matches with operands containing a dash operation followed by anything`() {
        // GIVEN
        val operands = termsOf(
                dashOperation(),
                anything(),
                anything())

        // THEN
        assertTrue(DashOperationAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and anything matcher matches with operands containing a dash operation followed by a dash operation`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                dashOperation(),
                dashOperation())

        // THEN
        assertTrue(DashOperationAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and anything matcher matches with operands containing exclusively dash operations`() {
        // GIVEN
        val operands = termsOf(
                dashOperation(),
                dashOperation(),
                dashOperation())

        // THEN
        assertTrue(DashOperationAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and anything matcher doesn't match with operands containing zero dash operations`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(DashOperationAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and anything matcher doesn't match with operands containing a dash operation not followed by anything`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                dashOperation())

        // THEN
        assertFalse(DashOperationAndAnythingMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and anything matcher matches with a left side consisting of a single dash operation and a right side consisting of a single term`() {
        // GIVEN
        val left = termsOf(dashOperation())
        val right = termsOf(anything())

        // THEN
        assertTrue(DashOperationAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and anything matcher matches with a left side consisting of a single dash operation and a right side consisting of several terms`() {
        // GIVEN
        val left = termsOf(dashOperation())
        val right = termsOf(anything(), anything())

        // THEN
        assertTrue(DashOperationAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and anything matcher doesn't match with a left side consisting of several dash operations`() {
        // GIVEN
        val left = termsOf(dashOperation(), dashOperation())
        val right = termsOf(anything())

        // THEN
        assertFalse(DashOperationAndAnythingMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and anything matcher doesn't match with a left side consisting of a single term other than a dash operation`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(DashOperationAndAnythingMatcher.matches(left, right))
    }

    private fun dashOperation(): DashOperation = mock()

    private fun anything(): Term = mock()
}