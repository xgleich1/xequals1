package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndDivisionExtractorTest {
    @Test
    fun `Should extract the first division as the left & the second as the right side from operands containing exactly two divisions`() {
        // GIVEN
        val divisionA = division()
        val divisionB = division()
        val operands = termsOf(
                anything(),
                divisionA,
                anything(),
                divisionB,
                anything())

        // THEN
        val expectedLeft = termsOf(divisionA)
        val expectedRight = termsOf(divisionB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first division as the left & the second as the right side from operands containing more than two divisions`() {
        // GIVEN
        val divisionA = division()
        val divisionB = division()
        val operands = termsOf(
                anything(),
                divisionA,
                anything(),
                divisionB,
                anything(),
                division(),
                anything())

        // THEN
        val expectedLeft = termsOf(divisionA)
        val expectedRight = termsOf(divisionB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()

    private fun extract(operands: Terms) = DivisionAndDivisionExtractor.extract(operands)
}