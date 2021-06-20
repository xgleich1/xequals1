package com.dg.eqs.core.definition.term.item.value

import com.dg.eqs.classes.negativeValue
import com.dg.eqs.classes.positiveValue
import com.dg.eqs.classes.value
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class ValueTest {
    @Test(expected = IllegalArgumentException::class)
    fun `A values unsigned number must be unsigned`() {
        value(-1)
    }

    @Test
    fun `A positive values signed number is positive`() {
        // GIVEN
        val value = positiveValue(1)

        // THEN
        assertThat(value.signedNumber).isEqualTo(1)
    }

    @Test
    fun `A negative values signed number is negative`() {
        // GIVEN
        val value = negativeValue(1)

        // THEN
        assertThat(value.signedNumber).isEqualTo(-1)
    }

    @Test
    fun `A value with a unsigned number of 1 is a one`() {
        // GIVEN
        val value = value(1)

        // THEN
        assertTrue(value.isOne)
    }

    @Test
    fun `A value with a unsigned number other than 1 isn't a one`() {
        // GIVEN
        val value = value(2)

        // THEN
        assertFalse(value.isOne)
    }

    @Test
    fun `A value with a unsigned number of 0 is a zero`() {
        // GIVEN
        val value = value(0)

        // THEN
        assertTrue(value.isZero)
    }

    @Test
    fun `A value with a unsigned number other than 0 isn't a zero`() {
        // GIVEN
        val value = value(1)

        // THEN
        assertFalse(value.isZero)
    }

    @Test
    fun `Should compare two equal values`() {
        // GIVEN
        val valueA = value(1)
        val valueB = value(1)

        // THEN
        assertThat(valueA).isEqualTo(valueB)
    }

    @Test
    fun `Should convert a positive value to a string by using the number without the sign`() {
        assertThat(positiveValue(1)).hasToString("1")
    }

    @Test
    fun `Should convert a negative value to a string by using the number with the sign`() {
        assertThat(negativeValue(1)).hasToString("-1")
    }

    @Test
    fun `Should not loose the sign when a negative value of zero is converted to a string`() {
        assertThat(negativeValue(0)).hasToString("-0")
    }

    @Test
    fun `A value is constant`() {
        assertTrue(value(1).isConstant())
    }
}