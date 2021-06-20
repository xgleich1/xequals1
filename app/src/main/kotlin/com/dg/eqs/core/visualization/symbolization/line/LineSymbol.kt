package com.dg.eqs.core.visualization.symbolization.line

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.symbolization.SymbolDraft


abstract class LineSymbol<out T : AnyOrigin>(origin: T) : SymbolDraft<T>(origin) {
    override fun draft(pencil: Pencil) = apply {
        height = pencil.lineSymbolHeight
    }

    override fun scale(factor: Float) = apply {
        height = (height * factor).toInt()
    }
}