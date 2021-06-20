package com.dg.eqs.core.definition.term.item.variable

import com.dg.eqs.asserts.assert_equals_by_value_only
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Test


class PositiveVariableTest {
    @Test
    fun `A positive variable is not negative`() {
        // GIVEN
        val variable = PositiveVariable("x")

        // THEN
        assertFalse(variable.isNegative)
    }

    @Test
    fun `A positive variables unsigned name equals the one its initialized with`() {
        // GIVEN
        val variable = PositiveVariable("x")

        // THEN
        assertThat(variable.unsignedName).isEqualTo("x")
    }

    @Test
    fun `Should invert the plus sign of a positive variable to a minus sign`() {
        // GIVEN
        val variable = PositiveVariable("x")

        // THEN
        assertThat(variable.invert()).isEqualTo(NegativeVariable("x"))
    }

    @Test
    fun `Should copy a positive variable`() {
        // GIVEN
        val variable = PositiveVariable("x")

        // THEN
        assert_equals_by_value_only(variable.copy(), variable)
    }
}