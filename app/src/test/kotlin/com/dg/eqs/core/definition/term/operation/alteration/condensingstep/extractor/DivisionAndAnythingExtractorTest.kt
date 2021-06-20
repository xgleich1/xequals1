package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndAnythingExtractorTest {
    @Test
    fun `Should extract the division as the left & its successor as the right side from operands containing a division followed by anything`() {
        // GIVEN
        val division = division()
        val anything = anything()
        val operands = termsOf(
                division,
                anything,
                anything())

        // THEN
        val expectedLeft = termsOf(division)
        val expectedRight = termsOf(anything)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first division as the left & the second as the right side from operands containing a division followed by a division`() {
        // GIVEN
        val divisionA = division()
        val divisionB = division()
        val operands = termsOf(
                anything(),
                divisionA,
                divisionB)

        // THEN
        val expectedLeft = termsOf(divisionA)
        val expectedRight = termsOf(divisionB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first division as the left & the second as the right side from operands containing exclusively divisions`() {
        // GIVEN
        val divisionA = division()
        val divisionB = division()
        val operands = termsOf(
                divisionA,
                divisionB,
                division())

        // THEN
        val expectedLeft = termsOf(divisionA)
        val expectedRight = termsOf(divisionB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun division(): Division = mock()

    private fun anything(): Term = mock()

    private fun extract(operands: Terms) = DivisionAndAnythingExtractor.extract(operands)
}