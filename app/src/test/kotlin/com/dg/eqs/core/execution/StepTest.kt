package com.dg.eqs.core.execution

import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class StepTest {
    @Test
    fun `A valid step is valid`() {
        assertTrue(ValidStep(mock(), mock()).isValid)
    }

    @Test
    fun `A valid step is not invalid`() {
        assertFalse(ValidStep(mock(), mock()).isInvalid)
    }

    @Test
    fun `A invalid step is not valid`() {
        assertFalse(InvalidStep(mock(), mock()).isValid)
    }

    @Test
    fun `A invalid step is invalid`() {
        assertTrue(InvalidStep(mock(), mock()).isInvalid)
    }
}