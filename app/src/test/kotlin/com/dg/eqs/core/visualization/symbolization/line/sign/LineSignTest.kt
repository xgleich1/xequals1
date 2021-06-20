package com.dg.eqs.core.visualization.symbolization.line.sign

import com.dg.eqs.base.abbreviation.AnyLineSign
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.INITIAL
import org.mockito.kotlin.mock
import org.junit.Assert.assertNull
import org.junit.Test


class LineSignTest {
    @Test
    fun `A line sign is not touchable`() {
        // GIVEN
        val lineSign = lineSign()

        // WHEN
        val touched = lineSign.touch(touch(
                hitTouchX(lineSign),
                hitTouchY(lineSign)))

        // THEN
        assertNull(touched)
    }

    private fun touch(x: Int, y: Int) = Touch(x, y, INITIAL)

    private fun hitTouchX(lineSign: AnyLineSign) = lineSign.centerX

    private fun hitTouchY(lineSign: AnyLineSign) = lineSign.centerY

    private fun lineSign(origin: AnyOrigin = mock()) = TestAnyLineSign(origin)

    private class TestAnyLineSign(origin: AnyOrigin) : LineSign<AnyOrigin>(origin)
}