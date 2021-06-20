package com.dg.eqs.base.extension

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class IntExtTest {
    @Test
    fun `A integer is even when its completely divisible through two`() {
        assertTrue(4.isEven)
    }

    @Test
    fun `A integer is not even when its not completely divisible through two`() {
        assertFalse(5.isEven)
    }

    @Test
    fun `A integer is odd when its not completely divisible through two`() {
        assertTrue(5.isOdd)
    }

    @Test
    fun `A integer is not odd when its completely divisible through two`() {
        assertFalse(4.isOdd)
    }
}