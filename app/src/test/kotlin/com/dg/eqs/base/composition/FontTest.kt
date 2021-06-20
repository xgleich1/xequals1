package com.dg.eqs.base.composition

import android.graphics.Typeface
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FontTest {
    @Mock
    private lateinit var fontType: Typeface


    @Test
    fun `Should scale a font by scaling its size`() {
        // GIVEN
        val font = Font(fontType, 1.0f)

        // WHEN
        val scaledFont = font.scale(0.5f)

        // THEN
        val expectedFont = Font(fontType, 0.5f)

        assertThat(scaledFont).isEqualTo(expectedFont)
    }
}