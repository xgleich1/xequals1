package com.dg.eqs.core.definition.term.operation.alteration.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class AnythingAndDivisionExtractorTest {
    @Test
    fun `Should extract the division as the right & its predecessor as the left side from operands containing anything before a division`() {
        // GIVEN
        val anything = anything()
        val division = division()
        val operands = termsOf(
                anything(),
                anything,
                division)

        // THEN
        val expectedLeft = termsOf(anything)
        val expectedRight = termsOf(division)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last division as the right & the second last as the left side from operands containing a division before a division`() {
        // GIVEN
        val divisionA = division()
        val divisionB = division()
        val operands = termsOf(
                divisionA,
                divisionB,
                anything())

        // THEN
        val expectedLeft = termsOf(divisionA)
        val expectedRight = termsOf(divisionB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last division as the right & the second last as the left side from operands containing exclusively divisions`() {
        // GIVEN
        val divisionB = division()
        val divisionC = division()
        val operands = termsOf(
                division(),
                divisionB,
                divisionC)

        // THEN
        val expectedLeft = termsOf(divisionB)
        val expectedRight = termsOf(divisionC)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()

    private fun extract(operands: Terms) = AnythingAndDivisionExtractor.extract(operands)
}