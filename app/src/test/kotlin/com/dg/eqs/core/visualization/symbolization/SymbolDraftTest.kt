package com.dg.eqs.core.visualization.symbolization

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.symbolsOf
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.Pencil
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Test


class SymbolDraftTest {
    @Test
    fun `A symbol drafts baseline is its vertical center`() {
        // GIVEN
        val symbol = symbol(height = 100, firstY = 25)

        // THEN
        assertThat(symbol.baseline).isEqualTo(75)
    }

    @Test
    fun `Should horizontally move a symbol draft`() {
        // GIVEN
        val symbol = symbol(firstX = 50)

        // WHEN
        symbol.moveX(100)

        // THEN
        assertThat(symbol.firstX).isEqualTo(150)
    }

    @Test
    fun `Should vertically move a symbol draft`() {
        // GIVEN
        val symbol = symbol(firstY = 50)

        // WHEN
        symbol.moveY(100)

        // THEN
        assertThat(symbol.firstY).isEqualTo(150)
    }

    @Test
    fun `A symbol draft unravels into itself`() {
        // GIVEN
        val symbol = symbol()

        // THEN
        assertThat(symbol.unravel()).isEqualTo(symbolsOf(symbol))
    }

    @Test
    fun `A symbol draft involves itself`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val symbolA = symbol(origin)
        val symbolB = symbol(origin)

        // THEN
        assertTrue(symbolA in symbolA)
        assertTrue(symbolB !in symbolA)
    }

    private fun symbol(origin: AnyOrigin = mock(), width: Int = 0, height: Int = 0, firstX: Int = 0, firstY: Int = 0) =
            TestAnySymbolDraft(origin).also { it.width = width; it.height = height; it.firstX = firstX; it.firstY = firstY }

    private class TestAnySymbolDraft(origin: AnyOrigin) : SymbolDraft<AnyOrigin>(origin) {
        override fun draft(pencil: Pencil) = TODO("not implemented")

        override fun scale(factor: Float) = TODO("not implemented")

        override fun touch(touch: Touch) = TODO("not implemented")
    }
}