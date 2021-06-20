package com.dg.eqs.core.visualization.symbolization.text.sign

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.text.TextSymbol


abstract class TextSign<out T : AnyOrigin>(origin: T, sign: String) : TextSymbol<T>(origin, sign) {
    override fun touch(touch: Touch): TermDraft? = null

    override fun specifyWidthPadding(textWidth: Int, pencil: Pencil) = 0

    override fun specifyHeightPadding(textHeight: Int, pencil: Pencil) = 0
}