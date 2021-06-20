package com.dg.eqs.core.visualization.symbolization

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.symbolsOf
import com.dg.eqs.core.visualization.Draft


abstract class SymbolDraft<out T : AnyOrigin>(origin: T) : Draft<T>(origin) {
    override val baseline get() = centerY


    override fun moveX(amount: Int) = apply {
        firstX += amount
    }

    override fun moveY(amount: Int) = apply {
        firstY += amount
    }

    override fun unravel() = symbolsOf(this)

    override fun contains(draft: AnyDraft) = this === draft
}