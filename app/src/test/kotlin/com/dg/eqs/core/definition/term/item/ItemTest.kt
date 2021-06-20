package com.dg.eqs.core.definition.term.item

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.classes.item
import org.mockito.kotlin.mock
import org.junit.Assert.assertTrue
import org.junit.Test


class ItemTest {
    @Test
    fun `A item is always solved`() {
        // GIVEN
        val item = item()

        // THEN
        assertTrue(item.isSolved())
    }

    @Test(expected = IllegalArgumentException::class)
    fun `A item cannot be removed`() {
        // GIVEN
        val item = item()

        // WHEN
        item.remove(item)
    }

    @Test // No exception expected
    fun `A item uses identity to check if it is removed`() {
        // GIVEN
        val item = item()
        val equalItem = item()

        // THEN
        item.remove(equalItem)
    }

    @Test
    fun `A item returns itself when it isn't removed`() {
        // GIVEN
        val item = item()

        // THEN
        assertTrue(item.remove(mock()) === item)
    }

    @Test
    fun `A item can be replaced with an replacement`() {
        // GIVEN
        val item = item()

        val new: Term = mock()

        // THEN
        assertTrue(item.replace(item, new) === new)
    }

    @Test
    fun `A item uses identity to check if it is replaced`() {
        // GIVEN
        val item = item()
        val equalItem = item()

        val new: Term = mock()

        // THEN
        assertTrue(item.replace(equalItem, new) !== new)
    }

    @Test
    fun `A item returns itself when it isn't replaced`() {
        // GIVEN
        val item = item()

        // THEN
        assertTrue(item.replace(mock(), mock()) === item)
    }

    @Test
    fun `A item cannot be further resolved`() {
        // GIVEN
        val item = item()

        // THEN
        assertTrue(item.resolve() === item)
    }
}