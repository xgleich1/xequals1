package com.dg.eqs.core.definition.term.operation.dotoperation.division

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.base.extension.and
import com.dg.eqs.core.definition.term.Term
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeDivisionTest {
    @Test
    fun `A negative division is negative`() {
        assertTrue(NegativeDivision(anything(), anything()).isNegative)
    }

    @Test
    fun `Should convert a negative division to a string`() {
        // GIVEN
        val numerator: Term = mock { on { toString() } doReturn "1" }
        val denominator: Term = mock { on { toString() } doReturn "2" }
        val division = NegativeDivision(numerator, denominator)

        // THEN
        assertThat(division).hasToString("-/[1, 2]")
    }

    @Test
    fun `Should invert the minus sign of a negative division to a plus sign`() {
        // GIVEN
        val numerator = anything()
        val denominator = anything()
        val division = NegativeDivision(numerator, denominator)

        // THEN
        val expectedDivision = PositiveDivision(numerator, denominator)

        assertThat(division.invert()).isEqualTo(expectedDivision)
    }

    @Test
    fun `Should recreate a negative division with two new operands`() {
        // GIVEN
        val division = NegativeDivision(anything(), anything())

        val newOperandA = anything()
        val newOperandB = anything()
        val newOperands = termsOf(newOperandA, newOperandB)

        // WHEN
        val recreation = division.recreate(newOperands)

        // THEN
        val expectedRecreation = NegativeDivision(newOperandA, newOperandB)

        assertThat(recreation).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should recreate a negative division with a new numerator & denominator`() {
        // GIVEN
        val division = NegativeDivision(anything(), anything())

        val newNumerator = anything()
        val newDenominator = anything()

        // WHEN
        val recreation = division.recreate(newNumerator, newDenominator)

        // THEN
        val expectedRecreation = NegativeDivision(newNumerator, newDenominator)

        assertThat(recreation).isEqualTo(expectedRecreation)
    }

    @Test
    fun `Should apply the sign of a negative division by inverting the numerators sign`() {
        // GIVEN
        val (numerator, numeratorOpposite) = anythingAndOpposite()
        val denominator = anything()
        val division = NegativeDivision(numerator, denominator)

        // THEN
        val expectedResult = PositiveDivision(numeratorOpposite, denominator)

        assertThat(division.applySign()).isEqualTo(expectedResult)
    }

    private fun anything(): Term = mock()

    private fun anythingAndOpposite(): Pair<Term, Term> {
        val anything = anything()
        val opposite = anything()

        whenever(anything.invert())
                .thenReturn(opposite)

        return anything and opposite
    }
}