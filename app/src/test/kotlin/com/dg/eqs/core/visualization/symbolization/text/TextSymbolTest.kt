package com.dg.eqs.core.visualization.symbolization.text

import android.graphics.Typeface
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.composition.Font
import com.dg.eqs.base.composition.Size
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.classes.font
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TextSymbolTest {
    private companion object {
        private const val WIDTH_PADDING = 10
        private const val HEIGHT_PADDING = 20
    }

    @Mock
    private lateinit var fontType: Typeface


    @Test
    fun `A text symbol provides its font type`() {
        // GIVEN
        val textSymbol = textSymbol()

        // WHEN
        textSymbol.draft(pencil(font(fontType)))

        // THEN
        assertThat(textSymbol.fontType).isEqualTo(fontType)
    }

    @Test
    fun `A text symbol provides its font size`() {
        // GIVEN
        val textSymbol = textSymbol()

        // WHEN
        textSymbol.draft(pencil(font(fontSize = 50f)))

        // THEN
        assertThat(textSymbol.fontSize).isEqualTo(50f)
    }

    @Test(expected = IllegalStateException::class)
    fun `A text symbol throws an exception when its font type is accessed before its drafted`() {
        textSymbol().fontType
    }

    @Test(expected = IllegalStateException::class)
    fun `A text symbol throws an exception when its font size is accessed before its drafted`() {
        textSymbol().fontSize
    }

    @Test
    fun `Should compare two equal text symbols`() {
        // GIVEN
        val origin: AnyOrigin = mock()
        val textSymbolA = textSymbol(origin, "A")
        val textSymbolB = textSymbol(origin, "A")

        // THEN
        assertThat(textSymbolA).isEqualTo(textSymbolB)
    }

    @Test
    fun `Should convert a text symbol to a string`() {
        assertThat(textSymbol(text = "A")).hasToString("A")
    }

    @Test
    fun `Should draft a text symbol`() {
        // GIVEN
        val font: Font = mock {
            on { measure("A") } doReturn Size(30, 40)
        }

        val textSymbol = textSymbol(text = "A")

        // WHEN
        textSymbol.draft(pencil(font))

        // THEN
        assertThat(textSymbol.width).isEqualTo(40)
        assertThat(textSymbol.height).isEqualTo(60)
    }

    @Test
    fun `Should scale a text symbol`() {
        // GIVEN
        val scaledFont: Font = mock {
            on { measure("A") } doReturn Size(10, 20)
        }

        val font: Font = mock {
            on { measure("A") } doReturn Size(30, 40)

            on { scale(0.5f) } doReturn scaledFont
        }

        val textSymbol = textSymbol(text = "A")

        textSymbol.draft(pencil(font))

        // WHEN
        textSymbol.scale(0.5f)

        // THEN
        assertThat(textSymbol.width).isEqualTo(15)
        assertThat(textSymbol.height).isEqualTo(30)
    }

    @Test(expected = IllegalStateException::class)
    fun `A text symbol throws an exception when its scaled before its drafted`() {
        textSymbol().scale(0.5f)
    }

    private fun textSymbol(origin: AnyOrigin = mock(), text: String = "") = TestAnyTextSymbol(origin, text)

    private class TestAnyTextSymbol(origin: AnyOrigin, text: String) : TextSymbol<AnyOrigin>(origin, text) {
        override fun touch(touch: Touch) = TODO("not implemented")

        override fun specifyWidthPadding(textWidth: Int, pencil: Pencil) = WIDTH_PADDING

        override fun specifyHeightPadding(textHeight: Int, pencil: Pencil) = HEIGHT_PADDING
    }
}