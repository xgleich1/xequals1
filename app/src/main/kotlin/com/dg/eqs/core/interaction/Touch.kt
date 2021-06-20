package com.dg.eqs.core.interaction

import android.view.MotionEvent
import android.view.MotionEvent.*
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.core.interaction.Touch.Action.*


data class Touch(val x: Int, val y: Int, val action: Action) {
    enum class Action { INITIAL, ENSUING, CEASING, ABORTED }


    constructor(motionEvent: MotionEvent) : this(
            motionEvent.x.toInt(), motionEvent.y.toInt(), motionEvent.action.toAction())

    infix fun hits(draft: AnyDraft?) = draft != null && hitsAbsoluteX(draft) && hitsExtendedY(draft)

    private fun hitsAbsoluteX(draft: AnyDraft) = x >= draft.firstX && x <= draft.finalX

    private fun hitsExtendedY(draft: AnyDraft) = y >= draft.firstY && y <= draft.finalY + draft.halfHeight

    private companion object {
        private fun Int.toAction() = when(this) {
            ACTION_DOWN -> INITIAL
            ACTION_MOVE -> ENSUING
            ACTION_UP -> CEASING

            else -> ABORTED
        }
    }
}