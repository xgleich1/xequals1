package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionThroughPositiveOneAndAnythingExtractorTest {
    @Test
    fun `Should extract the division through positive one as the left & its successor as the right side from operands containing a division through positive one followed by anything`() {
        // GIVEN
        val divisionThroughPositiveOne = divisionThroughPositiveOne()
        val anything = anything()
        val operands = termsOf(
                division(),
                anything(),
                divisionThroughPositiveOne,
                anything,
                division())

        // THEN
        val expectedLeft = termsOf(divisionThroughPositiveOne)
        val expectedRight = termsOf(anything)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the division through positive one as the left & the division after it as the right side from operands containing a division through positive one followed by a division`() {
        // GIVEN
        val divisionThroughPositiveOne = divisionThroughPositiveOne()
        val division = division()
        val operands = termsOf(
                anything(),
                division(),
                divisionThroughPositiveOne,
                division,
                anything())

        // THEN
        val expectedLeft = termsOf(divisionThroughPositiveOne)
        val expectedRight = termsOf(division)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first division through positive one as the left & the second as the right side from operands containing a division through positive one followed by a division through positive one`() {
        // GIVEN
        val divisionThroughPositiveOneA = divisionThroughPositiveOne()
        val divisionThroughPositiveOneB = divisionThroughPositiveOne()
        val operands = termsOf(
                division(),
                anything(),
                divisionThroughPositiveOneA,
                divisionThroughPositiveOneB,
                anything(),
                division())

        // THEN
        val expectedLeft = termsOf(divisionThroughPositiveOneA)
        val expectedRight = termsOf(divisionThroughPositiveOneB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first division through positive one as the left & the second as the right side from operands containing exclusively divisions through positive one`() {
        // GIVEN
        val divisionThroughPositiveOneA = divisionThroughPositiveOne()
        val divisionThroughPositiveOneB = divisionThroughPositiveOne()
        val divisionThroughPositiveOneC = divisionThroughPositiveOne()
        val operands = termsOf(
                divisionThroughPositiveOneA,
                divisionThroughPositiveOneB,
                divisionThroughPositiveOneC)

        // THEN
        val expectedLeft = termsOf(divisionThroughPositiveOneA)
        val expectedRight = termsOf(divisionThroughPositiveOneB)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()

    private fun divisionThroughPositiveOne(): Division = mock { on { isDivisionThroughPositiveOne } doReturn true }

    private fun extract(operands: Terms) = DivisionThroughPositiveOneAndAnythingExtractor.extract(operands)
}