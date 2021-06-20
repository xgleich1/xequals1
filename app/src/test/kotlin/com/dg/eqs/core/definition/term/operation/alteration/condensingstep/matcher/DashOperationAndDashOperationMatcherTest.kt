package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.matcher

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DashOperationAndDashOperationMatcherTest {
    @Test
    fun `A dash operation and dash operation matcher matches with operands containing exactly two dash operations`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                dashOperation(),
                anything(),
                dashOperation(),
                anything())

        // THEN
        assertTrue(DashOperationAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and dash operation matcher matches with operands containing more than two dash operations`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                dashOperation(),
                anything(),
                dashOperation(),
                anything(),
                dashOperation(),
                anything())

        // THEN
        assertTrue(DashOperationAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and dash operation matcher doesn't match with operands containing a single dash operation`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                dashOperation(),
                anything())

        // THEN
        assertFalse(DashOperationAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and dash operation matcher doesn't match with operands containing zero dash operations`() {
        // GIVEN
        val operands = termsOf(
                anything(),
                anything(),
                anything())

        // THEN
        assertFalse(DashOperationAndDashOperationMatcher.matches(operands))
    }

    @Test
    fun `A dash operation and dash operation matcher matches with a left & right side consisting of single dash operations`() {
        // GIVEN
        val left = termsOf(dashOperation())
        val right = termsOf(dashOperation())

        // THEN
        assertTrue(DashOperationAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and dash operation matcher doesn't match with a left side consisting of more than one dash operation`() {
        // GIVEN
        val left = termsOf(dashOperation(), dashOperation())
        val right = termsOf(dashOperation())

        // THEN
        assertFalse(DashOperationAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and dash operation matcher doesn't match with a right side consisting of more than one dash operation`() {
        // GIVEN
        val left = termsOf(dashOperation())
        val right = termsOf(dashOperation(), dashOperation())

        // THEN
        assertFalse(DashOperationAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and dash operation matcher doesn't match with only a right side consisting of a single dash operation`() {
        // GIVEN
        val left = termsOf(anything())
        val right = termsOf(dashOperation())

        // THEN
        assertFalse(DashOperationAndDashOperationMatcher.matches(left, right))
    }

    @Test
    fun `A dash operation and dash operation matcher doesn't match with only a left side consisting of a single dash operation`() {
        // GIVEN
        val left = termsOf(dashOperation())
        val right = termsOf(anything())

        // THEN
        assertFalse(DashOperationAndDashOperationMatcher.matches(left, right))
    }

    private fun dashOperation(): DashOperation = mock()

    private fun anything(): Term = mock()
}