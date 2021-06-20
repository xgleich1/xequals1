package com.dg.eqs.core.definition.term.item.variable

import com.dg.eqs.asserts.assert_equals_by_value_only
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class NegativeVariableTest {
    @Test
    fun `A negative variable is negative`() {
        // GIVEN
        val variable = NegativeVariable("x")

        // THEN
        assertTrue(variable.isNegative)
    }

    @Test
    fun `A negative variables unsigned name equals the one its initialized with`() {
        // GIVEN
        val variable = NegativeVariable("x")

        // THEN
        assertThat(variable.unsignedName).isEqualTo("x")
    }

    @Test
    fun `Should invert the minus sign of a negative variable to a plus sign`() {
        // GIVEN
        val variable = NegativeVariable("x")

        // THEN
        assertThat(variable.invert()).isEqualTo(PositiveVariable("x"))
    }

    @Test
    fun `Should copy a negative variable`() {
        // GIVEN
        val variable = NegativeVariable("x")

        // THEN
        assert_equals_by_value_only(variable.copy(), variable)
    }
}