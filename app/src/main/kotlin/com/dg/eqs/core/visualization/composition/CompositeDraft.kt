package com.dg.eqs.core.visualization.composition

import com.dg.eqs.base.abbreviation.AnyCompositeDraft
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyDrafts
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.Pencil


abstract class CompositeDraft<out T : AnyOrigin>(
        origin: T, val parts: AnyDrafts) : Draft<T>(origin) {

    private var paddingLeft = 0
    private var paddingRight = 0


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        if(!super.equals(other)) return false

        other as AnyCompositeDraft

        if(parts != other.parts) return false
        if(paddingLeft != other.paddingLeft) return false
        if(paddingRight != other.paddingRight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()

        result = 31 * result + parts.hashCode()
        result = 31 * result + paddingLeft
        result = 31 * result + paddingRight

        return result
    }

    override fun toString() = parts.toString()

    override fun draft(pencil: Pencil) = apply {
        parts.draft(pencil)

        specifyPadding(pencil)

        adjustParts()

        arrangeParts()

        defineOutline()
    }

    override fun moveX(amount: Int) = apply {
        parts.moveX(amount)

        firstX += amount
    }

    override fun moveY(amount: Int) = apply {
        parts.moveY(amount)

        firstY += amount
    }

    override fun scale(factor: Float) = apply {
        parts.scale(factor)

        scalePadding(factor)

        adjustParts()

        arrangeParts()

        defineOutline()
    }

    override fun unravel() = parts.unravel()

    override fun touch(touch: Touch) = parts.touch(touch)

    override fun contains(draft: AnyDraft) = this === draft || parts.any { draft in it }

    private fun specifyPadding(pencil: Pencil) {
        paddingLeft = specifyPaddingLeft(pencil)
        paddingRight = specifyPaddingRight(pencil)
    }

    private fun scalePadding(factor: Float) {
        paddingLeft = (paddingLeft * factor).toInt()
        paddingRight = (paddingRight * factor).toInt()
    }

    private fun adjustParts() = parts.forEach { part ->
        part.width = calculatePartWidth(part)
        part.height = calculatePartHeight(part)
    }

    private fun arrangeParts() = parts.forEachIndexed { index, part ->
        if(index > 0) {
            val base = parts[index - 1]

            part.moveX(calculatePartShiftX(base, part))
            part.moveY(calculatePartShiftY(base, part))
        }
    }

    private fun defineOutline() {
        width = parts.width + paddingLeft + paddingRight
        height = parts.height
        firstX = parts.firstX - paddingLeft
        firstY = parts.firstY
    }

    protected abstract fun specifyPaddingLeft(pencil: Pencil): Int

    protected abstract fun specifyPaddingRight(pencil: Pencil): Int

    protected abstract fun calculatePartWidth(part: AnyDraft): Int

    protected abstract fun calculatePartHeight(part: AnyDraft): Int

    protected abstract fun calculatePartShiftX(base: AnyDraft, part: AnyDraft): Int

    protected abstract fun calculatePartShiftY(base: AnyDraft, part: AnyDraft): Int
}