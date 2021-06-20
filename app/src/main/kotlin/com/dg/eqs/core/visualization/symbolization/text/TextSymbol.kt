package com.dg.eqs.core.visualization.symbolization.text

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.AnyTextSymbol
import com.dg.eqs.base.composition.Font
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.SymbolDraft


abstract class TextSymbol<out T : AnyOrigin>(
        origin: T, val text: String) : SymbolDraft<T>(origin) {

    val fontType get() = checkNotNull(font).type
    val fontSize get() = checkNotNull(font).size

    private var textWidth = 0
    private var textHeight = 0
    private var widthPadding = 0
    private var heightPadding = 0

    private var font: Font? = null


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        if(!super.equals(other)) return false

        other as AnyTextSymbol

        if(text != other.text) return false
        if(font != other.font) return false
        if(textWidth != other.textWidth) return false
        if(textHeight != other.textHeight) return false
        if(widthPadding != other.widthPadding) return false
        if(heightPadding != other.heightPadding) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()

        result = 31 * result + text.hashCode()
        result = 31 * result + font.hashCode()
        result = 31 * result + textWidth
        result = 31 * result + textHeight
        result = 31 * result + widthPadding
        result = 31 * result + heightPadding

        return result
    }

    override fun toString() = text

    override fun draft(pencil: Pencil) = apply {
        font = pencil.textSymbolFont

        measureTextSize()

        specifyPadding(pencil)

        defineOutline()
    }

    override fun scale(factor: Float) = apply {
        font = font?.scale(factor)

        measureTextSize()

        scalePadding(factor)

        defineOutline()
    }

    private fun measureTextSize() {
        val textSize = checkNotNull(font)
                .measure(text)

        textWidth = textSize.width
        textHeight = textSize.height
    }

    private fun specifyPadding(pencil: Pencil) {
        widthPadding = specifyWidthPadding(textWidth, pencil)
        heightPadding = specifyHeightPadding(textHeight, pencil)
    }

    private fun scalePadding(factor: Float) {
        widthPadding = (widthPadding * factor).toInt()
        heightPadding = (heightPadding * factor).toInt()
    }

    private fun defineOutline() {
        width = textWidth + widthPadding
        height = textHeight + heightPadding
    }

    protected abstract fun specifyWidthPadding(textWidth: Int, pencil: Pencil): Int

    protected abstract fun specifyHeightPadding(textHeight: Int, pencil: Pencil): Int
}