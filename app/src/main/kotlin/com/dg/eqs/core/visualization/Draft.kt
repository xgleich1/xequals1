package com.dg.eqs.core.visualization

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.base.abbreviation.AnySymbolDrafts
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.Touch


abstract class Draft<out T : AnyOrigin>(val origin: T) {
    var choosableParent: TermDraft? = null
    var choosableSuccessor: TermDraft? = null
    var choosablePredecessor: TermDraft? = null

    var width = 0
    var height = 0
    var firstX = 0
    var firstY = 0

    val halfWidth get() = width / 2
    val halfHeight get() = height / 2
    val centerX get() = firstX + halfWidth
    val centerY get() = firstY + halfHeight
    val finalX get() = firstX + width
    val finalY get() = firstY + height

    abstract val baseline: Int


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as AnyDraft

        if(origin != other.origin) return false
        if(width != other.width) return false
        if(height != other.height) return false
        if(firstX != other.firstX) return false
        if(firstY != other.firstY) return false
        if(baseline != other.baseline) return false

        return true
    }

    override fun hashCode(): Int {
        var result = origin.hashCode()

        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + firstX
        result = 31 * result + firstY
        result = 31 * result + baseline

        return result
    }

    fun isTouched(touch: Touch) = touch(touch) != null

    abstract fun draft(pencil: Pencil): Draft<T>

    abstract fun moveX(amount: Int): Draft<T>

    abstract fun moveY(amount: Int): Draft<T>

    abstract fun scale(factor: Float): Draft<T>

    abstract fun unravel(): AnySymbolDrafts

    abstract fun touch(touch: Touch): TermDraft?

    abstract operator fun contains(draft: AnyDraft): Boolean
}