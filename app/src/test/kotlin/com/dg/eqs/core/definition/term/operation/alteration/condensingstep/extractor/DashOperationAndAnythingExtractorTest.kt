package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndAnythingExtractorTest {
    @Test
    fun `Should extract the dash operation as the left & its successor as the right side from operands containing a dash operation followed by anything`() {
        // GIVEN
        val dashOperation = dashOperation()
        val anything = anything()
        val operands = termsOf(
                dashOperation,
                anything,
                anything())

        // THEN
        val expectedLeft = termsOf(dashOperation)
        val expectedRight = termsOf(anything)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first dash operation as the left & the second as the right side from operands containing a dash operation followed by a dash operation`() {
        // GIVEN
        val dashOperationA = dashOperation()
        val dashOperationB = dashOperation()
        val operands = termsOf(
                anything(),
                dashOperationA,
                dashOperationB)

        // THEN
        val expectedLeft = termsOf(dashOperationA)
        val expectedRight = termsOf(dashOperationB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first dash operation as the left & the second as the right side from operands containing exclusively dash operations`() {
        // GIVEN
        val dashOperationA = dashOperation()
        val dashOperationB = dashOperation()
        val operands = termsOf(
                dashOperationA,
                dashOperationB,
                dashOperation())

        // THEN
        val expectedLeft = termsOf(dashOperationA)
        val expectedRight = termsOf(dashOperationB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun dashOperation(): DashOperation = mock()

    private fun anything(): Term = mock()

    private fun extract(operands: Terms) = DashOperationAndAnythingExtractor.extract(operands)
}