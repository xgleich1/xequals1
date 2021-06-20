package com.dg.eqs.core.definition.term

import com.dg.eqs.classes.negativeTerm
import com.dg.eqs.classes.positiveTerm
import com.dg.eqs.classes.term
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TermTest {
    @Test
    fun `A positive term is positive`() {
        // GIVEN
        val term = positiveTerm()

        // THEN
        assertTrue(term.isPositive)
    }

    @Test
    fun `A negative term isn't positive`() {
        // GIVEN
        val term = negativeTerm()

        // THEN
        assertFalse(term.isPositive)
    }

    @Test
    fun `Should compare two equal terms`() {
        // GIVEN
        val termA = term()
        val termB = term()

        // THEN
        assertThat(termA).isEqualTo(termB)
    }
}