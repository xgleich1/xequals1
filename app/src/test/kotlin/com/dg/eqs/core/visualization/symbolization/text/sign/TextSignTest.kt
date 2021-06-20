package com.dg.eqs.core.visualization.symbolization.text.sign

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.AnyTextSign
import com.dg.eqs.base.composition.Size
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.INITIAL
import com.dg.eqs.classes.font
import com.dg.eqs.classes.pencil
import org.mockito.kotlin.mock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertNull
import org.junit.Test


class TextSignTest {
    @Test
    fun `A text sign is not touchable`() {
        // GIVEN
        val textSign = textSign()

        // WHEN
        val touched = textSign.touch(touch(
                hitTouchX(textSign),
                hitTouchY(textSign)))

        // THEN
        assertNull(touched)
    }

    @Test
    fun `A text sign has no padding around its sign`() {
        // GIVEN
        val textSign = textSign()

        // WHEN
        textSign.draft(pencil(font(
                textSize = Size(30, 40))))

        // THEN
        assertThat(textSign.width).isEqualTo(30)
        assertThat(textSign.height).isEqualTo(40)
    }

    private fun touch(x: Int, y: Int) = Touch(x, y, INITIAL)

    private fun hitTouchX(textSign: AnyTextSign) = textSign.centerX

    private fun hitTouchY(textSign: AnyTextSign) = textSign.centerY

    private fun textSign(origin: AnyOrigin = mock(), sign: String = "") = TestAnyTextSign(origin, sign)

    private class TestAnyTextSign(origin: AnyOrigin, sign: String) : TextSign<AnyOrigin>(origin, sign)
}