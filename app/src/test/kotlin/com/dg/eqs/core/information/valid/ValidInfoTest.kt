package com.dg.eqs.core.information.valid

import com.dg.eqs.classes.validInfo
import org.junit.Assert.assertTrue
import org.junit.Test


class ValidInfoTest {
    @Test
    fun `A valid info is valid`() {
        assertTrue(validInfo().isValid)
    }
}