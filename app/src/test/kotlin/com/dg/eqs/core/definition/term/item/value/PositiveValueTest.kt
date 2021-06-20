package com.dg.eqs.core.definition.term.item.value

import com.dg.eqs.asserts.assert_equals_by_value_only
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Test


class PositiveValueTest {
    @Test
    fun `A positive value is not negative`() {
        // GIVEN
        val value = PositiveValue(1)

        // THEN
        assertFalse(value.isNegative)
    }

    @Test
    fun `A positive values unsigned number equals the one its initialized with`() {
        // GIVEN
        val value = PositiveValue(1)

        // THEN
        assertThat(value.unsignedNumber).isEqualTo(1)
    }

    @Test
    fun `Should invert the plus sign of a positive value to a minus sign`() {
        // GIVEN
        val value = PositiveValue(1)

        // THEN
        assertThat(value.invert()).isEqualTo(NegativeValue(1))
    }

    @Test
    fun `Should copy a positive value`() {
        // GIVEN
        val value = PositiveValue(1)

        // THEN
        assert_equals_by_value_only(value.copy(), value)
    }
}