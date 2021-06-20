package com.dg.eqs.core.visualization

import com.dg.eqs.base.abbreviation.AnyDrafts
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.interaction.Touch


class Drafts<out T : AnyOrigin>(
        private val inner: List<Draft<T>>) : List<Draft<T>> by inner {

    val origins get() = map(Draft<T>::origin)

    val width  get() = finalX - firstX
    val height get() = finalY - firstY
    val firstX get() = checkNotNull(map(Draft<T>::firstX).minOrNull())
    val firstY get() = checkNotNull(map(Draft<T>::firstY).minOrNull())

    val centerX get() = firstX + width / 2
    val centerY get() = firstY + height / 2
    val finalX  get() = checkNotNull(map(Draft<T>::finalX).maxOrNull())
    val finalY  get() = checkNotNull(map(Draft<T>::finalY).maxOrNull())


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as AnyDrafts

        if(inner != other.inner) return false

        return true
    }

    override fun hashCode() = inner.hashCode()

    override fun toString() = inner.toString()

    fun draft(pencil: Pencil) = forEach { it.draft(pencil) }

    fun moveX(amount: Int) = forEach { it.moveX(amount) }

    fun moveY(amount: Int) = forEach { it.moveY(amount) }

    fun scale(factor: Float) = forEach { it.scale(factor) }

    fun unravel() = flatMap { it.unravel() }

    fun touch(touch: Touch) = mapNotNull { it.touch(touch) }.firstOrNull()
}