package com.dg.eqs.core.interaction.linking.choosing

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.linking.choosing.widening.SourceWidener
import com.dg.eqs.core.interaction.linking.choosing.widening.TargetWidener
import com.dg.eqs.core.visualization.composition.horizontal.relation.HorizontalRelation


class Chooser(
        private val sourceWidener: SourceWidener,
        private val targetWidener: TargetWidener) {

    val chosenSource = Choice()
    val chosenTarget = Choice()

    private var touchedSource: TermDraft? = null
    private var touchedTarget: TermDraft? = null


    fun commenceChoosing(draft: AnyDraft, touch: Touch) = onImmediateTouchResult(draft, touch) {
        onTouchedAllowedSource(it) {
            startSourceChoosing(it)
        }
    }

    fun continueChoosing(draft: AnyDraft, touch: Touch) = onAugmentedTouchResult(draft, touch) {
        onTouchedSourceLeft(it) {
            stopSourceWidening()
        }

        onTouchedTargetLeft(it) {
            stopTargetWidening()
            removeChosenTarget()
        }

        onTouchedAllowedTarget(it) {
            startTargetChoosing(it)
        }
    }

    fun stopChoosing() {
        stopSourceWidening()
        stopTargetWidening()
    }

    private inline fun onImmediateTouchResult(draft: AnyDraft, touch: Touch, on: (TermDraft?) -> Unit) {
        draft.touch(touch).let(on)
    }

    private inline fun onAugmentedTouchResult(draft: AnyDraft, touch: Touch, on: (TermDraft?) -> Unit) {
        onOtherSideTouchResult(draft, touch) {
            return on(it)
        }

        onImmediateTouchResult(draft, touch) {
            return on(it)
        }
    }

    private inline fun onOtherSideTouchResult(draft: AnyDraft, touch: Touch, on: (TermDraft) -> Unit) {
        if(draft is HorizontalRelation) draft.run {
            when {
                touch hits left && chosenSource absentIn left -> on(left)
                touch hits right && chosenSource absentIn right -> on(right)
            }
        }
    }

    private inline fun onTouchedAllowedSource(touchResult: TermDraft?, on: (TermDraft) -> Unit) {
        touchResult?.let(on)
    }

    private inline fun onTouchedAllowedTarget(touchResult: TermDraft?, on: (TermDraft) -> Unit) {
        touchResult?.let { if(it !in chosenSource && it !in chosenTarget) on(it) }
    }

    private inline fun onTouchedSourceLeft(touchResult: TermDraft?, action: () -> Unit) {
        touchedSource?.let { if(touchResult !== it) action() }
    }

    private inline fun onTouchedTargetLeft(touchResult: TermDraft?, action: () -> Unit) {
        touchedTarget?.let { if(touchResult !== it) action() }
    }

    private fun startSourceChoosing(touchResult: TermDraft) {
        touchedSource = touchResult

        chooseTouchedSource()
        startSourceWidening()
    }

    private fun startTargetChoosing(touchResult: TermDraft) {
        touchedTarget = touchResult

        chooseTouchedTarget()
        startTargetWidening()
    }

    private fun chooseTouchedSource() = touchedSource?.let(chosenSource::addInitialChosen)

    private fun chooseTouchedTarget() = touchedTarget?.let(chosenTarget::addInitialChosen)

    private fun startSourceWidening() = sourceWidener.start(chosenSource)

    private fun startTargetWidening() = targetWidener.start(chosenTarget, chosenSource)

    private fun stopSourceWidening() = sourceWidener.stop()

    private fun stopTargetWidening() = targetWidener.stop()

    private fun removeChosenTarget() = chosenTarget.clear()
}