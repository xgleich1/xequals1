package com.dg.eqs.core.definition.term.item.value

import com.dg.eqs.asserts.assert_equals_by_value_only
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeValueTest {
    @Test
    fun `A negative value is negative`() {
        // GIVEN
        val value = NegativeValue(1)

        // THEN
        assertTrue(value.isNegative)
    }

    @Test
    fun `A negative values unsigned number equals the one its initialized with`() {
        // GIVEN
        val value = NegativeValue(1)

        // THEN
        assertThat(value.unsignedNumber).isEqualTo(1)
    }

    @Test
    fun `Should invert the minus sign of a negative value to a plus sign`() {
        // GIVEN
        val value = NegativeValue(1)

        // THEN
        assertThat(value.invert()).isEqualTo(PositiveValue(1))
    }

    @Test
    fun `Should copy a negative value`() {
        // GIVEN
        val value = NegativeValue(1)

        // THEN
        assert_equals_by_value_only(value.copy(), value)
    }
}