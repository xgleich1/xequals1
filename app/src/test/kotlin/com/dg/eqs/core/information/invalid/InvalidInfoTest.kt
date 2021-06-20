package com.dg.eqs.core.information.invalid

import com.dg.eqs.classes.invalidInfo
import org.junit.Assert.assertFalse
import org.junit.Test


class InvalidInfoTest {
    @Test
    fun `A invalid info is not valid`() {
        assertFalse(invalidInfo().isValid)
    }
}