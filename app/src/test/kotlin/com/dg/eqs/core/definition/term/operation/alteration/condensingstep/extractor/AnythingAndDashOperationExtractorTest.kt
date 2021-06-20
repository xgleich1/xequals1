package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDashOperationExtractorTest {
    @Test
    fun `Should extract the dash operation as the right & its predecessor as the left side from operands containing anything before a dash operation`() {
        // GIVEN
        val anything = anything()
        val dashOperation = dashOperation()
        val operands = termsOf(
                anything(),
                anything,
                dashOperation)

        // THEN
        val expectedLeft = termsOf(anything)
        val expectedRight = termsOf(dashOperation)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last dash operation as the right & the second last as the left side from operands containing a dash operation before a dash operation`() {
        // GIVEN
        val dashOperationA = dashOperation()
        val dashOperationB = dashOperation()
        val operands = termsOf(
                dashOperationA,
                dashOperationB,
                anything())

        // THEN
        val expectedLeft = termsOf(dashOperationA)
        val expectedRight = termsOf(dashOperationB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last dash operation as the right & the second last as the left side from operands containing exclusively dash operations`() {
        // GIVEN
        val dashOperationB = dashOperation()
        val dashOperationC = dashOperation()
        val operands = termsOf(
                dashOperation(),
                dashOperationB,
                dashOperationC)

        // THEN
        val expectedLeft = termsOf(dashOperationB)
        val expectedRight = termsOf(dashOperationC)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun dashOperation(): DashOperation = mock()

    private fun extract(operands: Terms) = AnythingAndDashOperationExtractor.extract(operands)
}