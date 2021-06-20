package com.dg.eqs.core.visualization.symbolization.text.item

import com.dg.eqs.core.definition.term.item.Item
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.text.TextSymbol


abstract class TextItem<out T : Item>(origin: T, text: String) : TextSymbol<T>(origin, text) {
    override fun touch(touch: Touch) = takeIf { touch hits it }

    override fun specifyWidthPadding(textWidth: Int, pencil: Pencil) = with(pencil) {
        val paddedTextWidth = textWidth + textItemWidthPadding

        paddedTextWidth.coerceAtLeast(textItemMinSize) - textWidth
    }

    override fun specifyHeightPadding(textHeight: Int, pencil: Pencil) = with(pencil) {
        val paddedTextHeight = textHeight + textItemHeightPadding

        paddedTextHeight.coerceAtLeast(textItemMinSize) - textHeight
    }
}