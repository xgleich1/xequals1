package com.dg.eqs.core.definition.term.operation.dashoperation.condensingstep.executor

import com.dg.eqs.base.abbreviation.termsOf
import com.dg.eqs.core.definition.term.item.value.NegativeValue
import com.dg.eqs.core.definition.term.item.value.PositiveValue
import com.dg.eqs.core.definition.term.item.value.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ValueAndValueAdditionTest {
    @Test
    fun `Should add a positive value to a positive value`() {
        // GIVEN
        val left = PositiveValue(1)
        val right = PositiveValue(2)

        // THEN
        val expectedResult = termsOf(PositiveValue(3))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative value to a negative value`() {
        // GIVEN
        val left = NegativeValue(1)
        val right = NegativeValue(2)

        // THEN
        val expectedResult = termsOf(NegativeValue(3))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a positive value to a negative value`() {
        // GIVEN
        val left = PositiveValue(1)
        val right = NegativeValue(2)

        // THEN
        val expectedResult = termsOf(NegativeValue(1))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    @Test
    fun `Should add a negative value to a positive value`() {
        // GIVEN
        val left = NegativeValue(1)
        val right = PositiveValue(2)

        // THEN
        val expectedResult = termsOf(PositiveValue(1))

        assertThat(add(left, right)).isEqualTo(expectedResult)
    }

    private fun add(left: Value, right: Value) =
            ValueAndValueAddition.execute(left, right)
}