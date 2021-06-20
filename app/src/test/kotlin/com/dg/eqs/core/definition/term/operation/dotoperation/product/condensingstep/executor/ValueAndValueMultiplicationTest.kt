package com.dg.eqs.core.definition.term.operation.dotoperation.product.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ValueAndValueMultiplicationTest {
    @Test
    fun `Should multiply a positive value with a positive value`() {
        // GIVEN
        val left = PositiveValue(2)
        val right = PositiveValue(3)

        // THEN
        val expectedResult = termsOf(PositiveValue(6))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative value with a negative value`() {
        // GIVEN
        val left = NegativeValue(2)
        val right = NegativeValue(3)

        // THEN
        val expectedResult = termsOf(PositiveValue(6))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a positive value with a negative value`() {
        // GIVEN
        val left = PositiveValue(2)
        val right = NegativeValue(3)

        // THEN
        val expectedResult = termsOf(NegativeValue(6))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should multiply a negative value with a positive value`() {
        // GIVEN
        val left = NegativeValue(2)
        val right = PositiveValue(3)

        // THEN
        val expectedResult = termsOf(NegativeValue(6))

        assertThat(multiply(left, right)).isEqualTo(expectedResult)
    }

    private fun multiply(left: Value, right: Value) =
            ValueAndValueMultiplication.execute(left, right)
}