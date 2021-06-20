package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.dotoperation.division.NegativeDivision
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ValueAndValueWithGcdGreaterOneDivisionTest {
    @Test
    fun `Should divide a positive value through a positive value resulting in a single value`() {
        // GIVEN
        val numerator = PositiveValue(4)
        val denominator = PositiveValue(2)

        // THEN
        val expectedResult = termsOf(PositiveValue(2))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a positive value through a positive value resulting in a division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = PositiveValue(4)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveValue(1),
                PositiveValue(2)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative value through a negative value resulting in a single value`() {
        // GIVEN
        val numerator = NegativeValue(4)
        val denominator = NegativeValue(2)

        // THEN
        val expectedResult = termsOf(PositiveValue(2))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative value through a negative value resulting in a division`() {
        // GIVEN
        val numerator = NegativeValue(2)
        val denominator = NegativeValue(4)

        // THEN
        val expectedResult = termsOf(PositiveDivision(
                PositiveValue(1),
                PositiveValue(2)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a positive value through a negative value resulting in a single value`() {
        // GIVEN
        val numerator = PositiveValue(4)
        val denominator = NegativeValue(2)

        // THEN
        val expectedResult = termsOf(NegativeValue(2))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a positive value through a negative value resulting in a division`() {
        // GIVEN
        val numerator = PositiveValue(2)
        val denominator = NegativeValue(4)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveValue(1),
                PositiveValue(2)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative value through a positive value resulting in a single value`() {
        // GIVEN
        val numerator = NegativeValue(4)
        val denominator = PositiveValue(2)

        // THEN
        val expectedResult = termsOf(NegativeValue(2))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should divide a negative value through a positive value resulting in a division`() {
        // GIVEN
        val numerator = NegativeValue(2)
        val denominator = PositiveValue(4)

        // THEN
        val expectedResult = termsOf(NegativeDivision(
                PositiveValue(1),
                PositiveValue(2)))

        assertThat(divide(numerator, denominator)).isEqualTo(expectedResult)
    }

    private fun divide(numerator: Value, denominator: Value) =
            ValueAndValueWithGcdGreaterOneDivision.execute(numerator, denominator)
}