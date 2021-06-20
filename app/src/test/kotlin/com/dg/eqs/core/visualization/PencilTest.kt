package com.dg.eqs.core.visualization

import com.dg.eqs.base.composition.Font
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class PencilTest {
    @Mock
    private lateinit var textSymbolFont: Font


    @Test
    fun `Should compare two equal pencils`() {
        // GIVEN
        val pencilA = pencil(textSymbolFont,
                9, 8, 7, 6, 5, 4, 3, 2, 1)

        val pencilB = pencil(textSymbolFont,
                9, 8, 7, 6, 5, 4, 3, 2, 1)

        // THEN
        assertThat(pencilA).isEqualTo(pencilB)
    }

    @Test
    fun `Should convert a pencil to a string`() {
        // GIVEN
        whenever(textSymbolFont.toString()) doReturn "font"

        val pencil = pencil(textSymbolFont,
                9, 8, 7, 6, 5, 4, 3, 2, 1)

        // THEN
        assertThat(pencil).hasToString(
                "TestPencil(font, 9, 8, 7, 6, 5, 4, 3, 2, 1)")
    }
}