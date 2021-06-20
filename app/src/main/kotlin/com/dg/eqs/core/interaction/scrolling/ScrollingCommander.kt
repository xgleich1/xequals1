package com.dg.eqs.core.interaction.scrolling

import com.dg.eqs.base.composition.Size
import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.observation.Observable
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.ENSUING
import com.dg.eqs.core.interaction.Touch.Action.INITIAL
import com.dg.eqs.core.interaction.scrolling.ScrollingCommand.*


class ScrollingCommander {
    val commands = Observable<ScrollingCommand>()

    private val gridFirstX = 0
    private val gridFirstY = 0
    private var gridFinalX = 0
    private var gridFinalY = 0

    private var isScrollingActive = false

    private lateinit var previousTouch: Touch


    fun onGridLaidOut(gridSize: Size) {
        gridFinalX = gridSize.width
        gridFinalY = gridSize.height
    }

    fun onGridTouched(draft: AnyDraft, touch: Touch) {
        when(touch.action) {
            INITIAL -> handleInitialTouch(draft, touch)
            ENSUING -> handleEnsuingTouch(draft, touch)

            else -> handleCeasingTouch()
        }

        previousTouch = touch
    }

    private fun handleInitialTouch(draft: AnyDraft, touch: Touch) {
        if(!draft.isTouched(touch)) {
            isScrollingActive = true

            emitShowGridLines()
        }
    }

    private fun handleEnsuingTouch(draft: AnyDraft, touch: Touch) {
        if(isScrollingActive) {
            emitScrollGridX(calculateScrollAmountX(draft, touch))
            emitScrollGridY(calculateScrollAmountY(draft, touch))
        }
    }

    private fun handleCeasingTouch() {
        if(isScrollingActive) {
            emitHideGridLines()

            isScrollingActive = false
        }
    }

    private fun calculateScrollAmountX(draft: AnyDraft, touch: Touch) =
            with(draft) {
                val touchScrollAmount = touch.x - previousTouch.x

                if(touchScrollAmount < 0) {
                    -calculateLeftScrollDistance(-touchScrollAmount)
                } else {
                    calculateRightScrollDistance(touchScrollAmount)
                }
            }

    private fun calculateScrollAmountY(draft: AnyDraft, touch: Touch) =
            with(draft) {
                val touchScrollAmount = touch.y - previousTouch.y

                if(touchScrollAmount < 0) {
                    -calculateTopScrollDistance(-touchScrollAmount)
                } else {
                    calculateBottomScrollDistance(touchScrollAmount)
                }
            }

    private fun AnyDraft.calculateLeftScrollDistance(touchScrollDistance: Int) =
            touchScrollDistance.coerceAtMost(calculateLeftScrollMaxDistance())

    private fun AnyDraft.calculateRightScrollDistance(touchScrollDistance: Int) =
            touchScrollDistance.coerceAtMost(calculateRightScrollMaxDistance())

    private fun AnyDraft.calculateTopScrollDistance(touchScrollDistance: Int) =
            touchScrollDistance.coerceAtMost(calculateTopScrollMaxDistance())

    private fun AnyDraft.calculateBottomScrollDistance(touchScrollDistance: Int) =
            touchScrollDistance.coerceAtMost(calculateBottomScrollMaxDistance())

    private fun AnyDraft.calculateLeftScrollMaxDistance() =
            if(width <= gridFinalX) calculateLeftFreeSpace() else calculateRightOverlap()

    private fun AnyDraft.calculateRightScrollMaxDistance() =
            if(width <= gridFinalX) calculateRightFreeSpace() else calculateLeftOverlap()

    private fun AnyDraft.calculateTopScrollMaxDistance() =
            if(height <= gridFinalY) calculateTopFreeSpace() else calculateBottomOverlap()

    private fun AnyDraft.calculateBottomScrollMaxDistance() =
            if(height <= gridFinalY) calculateBottomFreeSpace() else calculateTopOverlap()

    private fun AnyDraft.calculateLeftFreeSpace() = if(firstX > gridFirstX) firstX else 0

    private fun AnyDraft.calculateLeftOverlap() = if(firstX < gridFirstX) -firstX else 0

    private fun AnyDraft.calculateRightFreeSpace() = if(finalX < gridFinalX) gridFinalX - finalX else 0

    private fun AnyDraft.calculateRightOverlap() = if(finalX > gridFinalX) finalX - gridFinalX else 0

    private fun AnyDraft.calculateTopFreeSpace() = if(firstY > gridFirstY) firstY else 0

    private fun AnyDraft.calculateTopOverlap() = if(firstY < gridFirstY) -firstY else 0

    private fun AnyDraft.calculateBottomFreeSpace() = if(finalY < gridFinalY) gridFinalY - finalY else 0

    private fun AnyDraft.calculateBottomOverlap() = if(finalY > gridFinalY) finalY - gridFinalY else 0

    private fun emitShowGridLines() = commands.emit(ShowGridLines)

    private fun emitScrollGridX(amountX: Int) = commands.emit(ScrollGridX(amountX))

    private fun emitScrollGridY(amountY: Int) = commands.emit(ScrollGridY(amountY))

    private fun emitHideGridLines() = commands.emit(HideGridLines)
}