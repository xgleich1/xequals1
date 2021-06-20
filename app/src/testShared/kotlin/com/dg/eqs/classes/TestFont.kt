package com.dg.eqs.classes

import android.graphics.Typeface
import com.dg.eqs.base.composition.Font
import com.dg.eqs.base.composition.Size
import com.dg.eqs.mocking.lenientMock
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


fun font(
        fontType: Typeface = mock(),
        fontSize: Float = 0f,
        textSize: Size = Size(0, 0)): Font = lenientMock {

    on { type } doReturn fontType
    on { size } doReturn fontSize

    on { measure(any()) } doReturn textSize
    on { scale(any()) } doReturn it
}