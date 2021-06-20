package com.dg.eqs.core.definition.term

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class TermExtTest {
    @Test
    fun `A term is a one when it is a value of one`() {
        // GIVEN
        val term: Term = mock<Value> {
            on { isOne } doReturn true
        }

        // THEN
        assertTrue(term.isOne)
    }

    @Test
    fun `A term cannot be a one when it isn't a value`() {
        // GIVEN
        val term: Term = mock()

        // THEN
        assertFalse(term.isOne)
    }

    @Test
    fun `A term is not a one when it is a value but not one`() {
        // GIVEN
        val term: Term = mock<Value> {
            on { isOne } doReturn false
        }

        // THEN
        assertFalse(term.isOne)
    }

    @Test
    fun `A term is a zero when it is a value of zero`() {
        // GIVEN
        val term: Term = mock<Value> {
            on { isZero } doReturn true
        }

        // THEN
        assertTrue(term.isZero)
    }

    @Test
    fun `A term cannot be a zero when it isn't a value`() {
        // GIVEN
        val term: Term = mock()

        // THEN
        assertFalse(term.isZero)
    }

    @Test
    fun `A term is not a zero when it is a value but not zero`() {
        // GIVEN
        val term: Term = mock<Value> {
            on { isZero } doReturn false
        }

        // THEN
        assertFalse(term.isZero)
    }

    @Test
    fun `A term is a division through positive one when it is a division through positive one`() {
        // GIVEN
        val term: Term = mock<Division> {
            on { isDivisionThroughPositiveOne } doReturn true
        }

        // THEN
        assertTrue(term.isDivisionThroughPositiveOne)
    }

    @Test
    fun `A term cannot be a division through positive one when it isn't a division`() {
        // GIVEN
        val term: Term = mock()

        // THEN
        assertFalse(term.isDivisionThroughPositiveOne)
    }

    @Test
    fun `A term is not a division through positive one when it is a division but not through positive one`() {
        // GIVEN
        val term: Term = mock<Division> {
            on { isDivisionThroughPositiveOne } doReturn false
        }

        // THEN
        assertFalse(term.isDivisionThroughPositiveOne)
    }
}