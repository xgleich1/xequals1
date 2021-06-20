package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class TermAndTermFromDivisionExtractorTest {
    @Test
    fun `Should extract the first term as the left & the second as the right side from a divisions operands`() {
        // GIVEN
        val firstTerm = term()
        val secondTerm = term()
        val operands = termsOf(
                firstTerm,
                secondTerm)

        // THEN
        val expectedLeft = termsOf(firstTerm)
        val expectedRight = termsOf(secondTerm)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun term(): Term = mock()

    private fun extract(operands: Terms) = TermAndTermFromDivisionExtractor.extract(operands)
}