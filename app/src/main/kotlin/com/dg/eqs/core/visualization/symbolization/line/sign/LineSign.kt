package com.dg.eqs.core.visualization.symbolization.line.sign

import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.symbolization.line.LineSymbol


abstract class LineSign<out T: AnyOrigin>(origin: T) : LineSymbol<T>(origin) {
    override fun touch(touch: Touch): TermDraft? = null
}