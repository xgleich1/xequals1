package com.dg.eqs.core.definition.term.item.variable

import com.dg.eqs.classes.negativeVariable
import com.dg.eqs.classes.positiveVariable
import com.dg.eqs.classes.variable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Test


class VariableTest {
    @Test(expected = IllegalArgumentException::class)
    fun `A variables unsigned name must not start with a plus sign`() {
        variable("+x")
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A variables unsigned name must not start with a minus sign`() {
        variable("-x")
    }

    @Test
    fun `Should compare two equal variables`() {
        // GIVEN
        val variableA = variable("x")
        val variableB = variable("x")

        // THEN
        assertThat(variableA).isEqualTo(variableB)
    }

    @Test
    fun `Should convert a positive variable to a string by using the name without the sign`() {
        assertThat(positiveVariable("x")).hasToString("x")
    }

    @Test
    fun `Should convert a negative variable to a string by using the name with the sign`() {
        assertThat(negativeVariable("x")).hasToString("-x")
    }

    @Test
    fun `A variable is not constant`() {
        assertFalse(variable("x").isConstant())
    }
}