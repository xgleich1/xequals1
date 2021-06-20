package com.dg.eqs.base.composition

import android.graphics.Typeface
import android.text.Layout.Alignment.ALIGN_NORMAL
import android.text.StaticLayout
import android.text.TextPaint
import kotlin.Int.Companion.MAX_VALUE


data class Font(val type: Typeface, val size: Float) {
    fun measure(text: String): Size {
        val staticLayout = StaticLayout(
                text,
                TextPaint().apply {
                    typeface = type
                    textSize = size
                },
                MAX_VALUE,
                ALIGN_NORMAL,
                1.0f,
                0.0f,
                false)

        val textWidth = staticLayout.getLineWidth(0).toInt()
        val textHeight = staticLayout.height

        return Size(textWidth, textHeight)
    }

    fun scale(factor: Float) = Font(type, size * factor)
}