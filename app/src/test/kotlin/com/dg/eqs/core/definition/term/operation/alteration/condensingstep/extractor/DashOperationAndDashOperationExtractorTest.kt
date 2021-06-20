package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DashOperationAndDashOperationExtractorTest {
    @Test
    fun `Should extract the first dash operation as the left & the second as the right side from operands containing exactly two dash operations`() {
        // GIVEN
        val dashOperationA = dashOperation()
        val dashOperationB = dashOperation()
        val operands = termsOf(
                anything(),
                dashOperationA,
                anything(),
                dashOperationB,
                anything())

        // THEN
        val expectedLeft = termsOf(dashOperationA)
        val expectedRight = termsOf(dashOperationB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first dash operation as the left & the second as the right side from operands containing more than two dash operations`() {
        // GIVEN
        val dashOperationA = dashOperation()
        val dashOperationB = dashOperation()
        val operands = termsOf(
                anything(),
                dashOperationA,
                anything(),
                dashOperationB,
                anything(),
                dashOperation(),
                anything())

        // THEN
        val expectedLeft = termsOf(dashOperationA)
        val expectedRight = termsOf(dashOperationB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun dashOperation(): DashOperation = mock()

    private fun extract(operands: Terms) = DashOperationAndDashOperationExtractor.extract(operands)
}