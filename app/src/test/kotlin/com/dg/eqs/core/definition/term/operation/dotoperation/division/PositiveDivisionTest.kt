package com.dg.eqs.core.definition.term.operation.dotoperation.division

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Test


class PositiveDivisionTest {
    @Test
    fun `A positive division is not negative`() {
        assertFalse(PositiveDivision(mock(), mock()).isNegative)
    }

    @Test
    fun `Should convert a positive division to a string`() {
        // GIVEN
        val numerator: Term = mock { on { toString() } doReturn "1" }
        val denominator: Term = mock { on { toString() } doReturn "2" }
        val division = PositiveDivision(numerator, denominator)

        // THEN
        assertThat(division).hasToString("+/[1, 2]")
    }

    @Test
    fun `Should invert the plus sign of a positive division to a minus sign`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator: Term = mock()
        val division = PositiveDivision(numerator, denominator)

        // THEN
        val expectedResult = NegativeDivision(numerator, denominator)

        assertThat(division.invert()).isEqualTo(expectedResult)
    }

    @Test
    fun `Should recreate a positive division with two new operands`() {
        // GIVEN
        val division = PositiveDivision(mock(), mock())

        val newOperandA: Term = mock()
        val newOperandB: Term = mock()
        val newOperands = termsOf(newOperandA, newOperandB)

        // WHEN
        val recreation = division.recreate(newOperands)

        // THEN
        val expectedRecreation = PositiveDivision(newOperandA, newOperandB)

        assertThat(recreation).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should recreate a positive division with a new numerator & denominator`() {
        // GIVEN
        val division = PositiveDivision(mock(), mock())

        val newNumerator: Term = mock()
        val newDenominator: Term = mock()

        // WHEN
        val recreation = division.recreate(newNumerator, newDenominator)

        // THEN
        val expectedRecreation = PositiveDivision(newNumerator, newDenominator)

        assertThat(recreation).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should not invert any signs when the sign of a positive division is applied`() {
        // GIVEN
        val numerator: Term = mock()
        val denominator: Term = mock()
        val division = PositiveDivision(numerator, denominator)

        // THEN
        assertThat(division.applySign()).isEqualTo(division)
    }
}