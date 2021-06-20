package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class AnythingAndDashOperationMatcherTest {
    @Test
    fun `A anything and dash operation matcher matches with operands containing anything before a dash operation`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                dashOperation())

        // THEN
        assertTrue(AnythingAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A anything and dash operation matcher matches with operands containing a dash operation before a dash operation`() {
        // GIVEN
        val operands = termsOf(
                dashOperation(),
                dashOperation(),
                anything())

        // THEN
        assertTrue(AnythingAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A anything and dash operation matcher matches with operands containing exclusively dash operations`() {
        // GIVEN
        val operands = termsOf(
                dashOperation(),
                dashOperation(),
                dashOperation())

        // THEN
        assertTrue(AnythingAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A anything and dash operation matcher doesn't match with operands containing zero dash operations`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A anything and dash operation matcher doesn't match with operands containing anything not followed by a dash operation`() {
        // GIVEN
        val operands = termsOf(
                dashOperation(),
                anything(),
                anything())

        // THEN
        assertFalse(AnythingAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A anything and dash operation matcher matches with a left side consisting of a single term and a right side consisting of a single dash operation`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(dashOperation())

        // THEN
        assertTrue(AnythingAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A anything and dash operation matcher matches with a left side consisting of several terms and a right side consisting of a single dash operation`() {
        // GIVEN
        val left = termsOf(anything(), anything())
        val right = termsOf(dashOperation())

        // THEN
        assertTrue(AnythingAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A anything and dash operation matcher doesn't match with a right side consisting of several dash operations`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(dashOperation(), dashOperation())

        // THEN
        assertFalse(AnythingAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A anything and dash operation matcher doesn't match with a right side consisting of a single term other than a dash operation`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(anything())

        // THEN
        assertFalse(AnythingAndDashOperationMatcher.matches(left, right))
    }

    private fun anything(): Term = mock()

    private fun dashOperation(): DashOperation = mock()
}