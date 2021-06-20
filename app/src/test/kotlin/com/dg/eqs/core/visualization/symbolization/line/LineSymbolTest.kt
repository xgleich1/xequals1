package com.dg.eqs.core.visualization.symbolization.line

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LineSymbolTest {
    @Test
    fun `Should draft a line symbol`() {
        // GIVEN
        val lineSymbol = lineSymbol()

        // WHEN
        lineSymbol.draft(pencil(
                1, 1, 1, 10, 1, 1, 1, 1, 1))

        // THEN
        assertThat(lineSymbol.width).isEqualTo(0)
        assertThat(lineSymbol.height).isEqualTo(10)
        assertThat(lineSymbol.firstX).isEqualTo(0)
        assertThat(lineSymbol.firstY).isEqualTo(0)
    }

    @Test
    fun `Should scale a line symbol`() {
        // GIVEN
        val lineSymbol = lineSymbol()

        lineSymbol.draft(pencil(
                1, 1, 1, 10, 1, 1, 1, 1, 1))

        // WHEN
        lineSymbol.scale(0.5f)

        // THEN
        assertThat(lineSymbol.height).isEqualTo(5)
        assertThat(lineSymbol.firstX).isEqualTo(0)
        assertThat(lineSymbol.firstY).isEqualTo(0)
    }

    private fun lineSymbol(origin: AnyOrigin = mock()) = TestAnyLineSymbol(origin)

    private class TestAnyLineSymbol(origin: AnyOrigin) : LineSymbol<AnyOrigin>(origin) {
        override fun touch(touch: Touch) = TODO("not implemented")
    }
}