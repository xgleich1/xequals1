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


class AnythingAndDivisionThroughPositiveOneExtractorTest {
    @Test
    fun `Should extract the division through positive one as the right & its predecessor as the left side from operands containing anything before a division through positive one`() {
        // GIVEN
        val anything = anything()
        val divisionThroughPositiveOne = divisionThroughPositiveOne()
        val operands = termsOf(
                division(),
                anything,
                divisionThroughPositiveOne,
                anything(),
                division())

        // THEN
        val expectedLeft = termsOf(anything)
        val expectedRight = termsOf(divisionThroughPositiveOne)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the division through positive one as the right & the division before it as the left side from operands containing a division before a division through positive one`() {
        // GIVEN
        val division = division()
        val divisionThroughPositiveOne = divisionThroughPositiveOne()
        val operands = termsOf(
                anything(),
                division,
                divisionThroughPositiveOne,
                division(),
                anything())

        // THEN
        val expectedLeft = termsOf(division)
        val expectedRight = termsOf(divisionThroughPositiveOne)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the last division through positive one as the right & the second last as the left side from operands containing a division through positive one before a division through positive one`() {
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
    fun `Should extract the last division through positive one as the right & the second last as the left side from operands containing exclusively divisions through positive one`() {
        // GIVEN
        val divisionThroughPositiveOneA = divisionThroughPositiveOne()
        val divisionThroughPositiveOneB = divisionThroughPositiveOne()
        val divisionThroughPositiveOneC = divisionThroughPositiveOne()
        val operands = termsOf(
                divisionThroughPositiveOneA,
                divisionThroughPositiveOneB,
                divisionThroughPositiveOneC)

        // THEN
        val expectedLeft = termsOf(divisionThroughPositiveOneB)
        val expectedRight = termsOf(divisionThroughPositiveOneC)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun division(): Division = mock()

    private fun divisionThroughPositiveOne(): Division = mock { on { isDivisionThroughPositiveOne } doReturn true }

    private fun extract(operands: Terms) = AnythingAndDivisionThroughPositiveOneExtractor.extract(operands)
}