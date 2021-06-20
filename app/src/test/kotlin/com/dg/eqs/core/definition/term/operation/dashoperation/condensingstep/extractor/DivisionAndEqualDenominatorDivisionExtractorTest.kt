package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.extractor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.and
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class DivisionAndEqualDenominatorDivisionExtractorTest {
    @Test
    fun `Should extract the first division as the left & the second as the right side from operands containing exactly two divisions with equal denominators`() {
        // GIVEN
        val firstDivisionWithEqualDenominator = division(anything(), denominatorA())
        val secondDivisionWithEqualDenominator = division(anything(), denominatorA())

        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything(),
                firstDivisionWithEqualDenominator,
                anything(),
                division(anything(), anything()),
                anything(),
                secondDivisionWithEqualDenominator,
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        val expectedLeft = termsOf(firstDivisionWithEqualDenominator)
        val expectedRight = termsOf(secondDivisionWithEqualDenominator)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first division as the left & the second as the right side from operands containing more than two divisions with equal denominators`() {
        // GIVEN
        val firstDivisionWithEqualDenominator = division(anything(), denominatorA())
        val secondDivisionWithEqualDenominator = division(anything(), denominatorA())
        val thirdDivisionWithEqualDenominator = division(anything(), denominatorA())

        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything(),
                firstDivisionWithEqualDenominator,
                anything(),
                division(anything(), anything()),
                anything(),
                secondDivisionWithEqualDenominator,
                anything(),
                division(anything(), anything()),
                anything(),
                thirdDivisionWithEqualDenominator,
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        val expectedLeft = termsOf(firstDivisionWithEqualDenominator)
        val expectedRight = termsOf(secondDivisionWithEqualDenominator)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    @Test
    fun `Should extract the first pair of divisions with equal denominators as the left & right side from operands containing different pairs of divisions with equal denominators`() {
        // GIVEN
        val firstDivisionWithEqualDenominatorA = division(anything(), denominatorA())
        val secondDivisionWithEqualDenominatorA = division(anything(), denominatorA())
        val firstDivisionWithEqualDenominatorB = division(anything(), denominatorB())
        val secondDivisionWithEqualDenominatorB = division(anything(), denominatorB())

        val operands = termsOf(
                anything(),
                division(anything(), anything()),
                anything(),
                firstDivisionWithEqualDenominatorA,
                anything(),
                division(anything(), anything()),
                anything(),
                secondDivisionWithEqualDenominatorA,
                anything(),
                division(anything(), anything()),
                anything(),
                firstDivisionWithEqualDenominatorB,
                anything(),
                division(anything(), anything()),
                anything(),
                secondDivisionWithEqualDenominatorB,
                anything(),
                division(anything(), anything()),
                anything())

        // THEN
        val expectedLeft = termsOf(firstDivisionWithEqualDenominatorA)
        val expectedRight = termsOf(secondDivisionWithEqualDenominatorA)

        assertThat(extract(operands)).isEqualTo(expectedLeft and expectedRight)
    }

    private fun anything(): Term = mock()

    private fun denominatorA() = positiveTerm()

    private fun denominatorB() = negativeTerm()

    private fun division(numerator: Term, denominator: Term): Division =
            mock {
                on { it.numerator } doReturn numerator
                on { it.denominator } doReturn denominator
            }

    private fun extract(operands: Terms) = DivisionAndEqualDenominatorDivisionExtractor.extract(operands)
}