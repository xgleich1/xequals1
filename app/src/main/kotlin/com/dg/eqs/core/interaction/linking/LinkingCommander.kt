package com.dg.eqs.core.interaction.linking

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.base.observation.Observable
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.interaction.Touch
import com.dg.eqs.core.interaction.Touch.Action.*
import com.dg.eqs.core.interaction.linking.LinkingCommand.*
import com.dg.eqs.core.interaction.linking.choosing.Chooser


class LinkingCommander(private val chooser: Chooser) {
    val commands = Observable<LinkingCommand>()

    private var markedSource = draftsOf<Term>()
    private var markedTarget = draftsOf<Term>()

    private val isSourceMarked get() = markedSource.isNotEmpty()
    private val isTargetMarked get() = markedTarget.isNotEmpty()

    private lateinit var currentTouch: Touch


    init {
        observeChosenSource()
        observeChosenTarget()
    }

    fun onRevertButtonClicked() = abortLinking()

    fun onInvertButtonClicked() = abortLinking()

    fun onSkipButtonClicked() = abortLinking()

    fun onInfoButtonClicked() = abortLinking()

    fun onGridTouched(draft: AnyDraft, touch: Touch) {
        currentTouch = touch

        return when(touch.action) {
            INITIAL -> commenceLinking(draft)
            ENSUING -> continueLinking(draft)
            CEASING -> completeLinking(draft)
            ABORTED -> abortLinking()
        }
    }

    private fun observeChosenSource() = chooser.chosenSource.observe {
        markedSource = it

        emitShowSourceMark()
        emitShowSourceArrow()
    }

    private fun observeChosenTarget() = chooser.chosenTarget.observe {
        markedTarget = it

        if(isTargetMarked) {
            emitShowTargetMark()
        } else {
            emitHideTargetMark()
        }
    }

    private fun commenceLinking(draft: AnyDraft) {
        if(isSourceMarked) {
            hideLinking()
        }

        commenceChoosing(draft)
    }

    private fun continueLinking(draft: AnyDraft) {
        if(isSourceMarked) {
            emitShowSourceArrow()

            continueChoosing(draft)
        }
    }

    private fun completeLinking(draft: AnyDraft) {
        if(isSourceMarked) {
            stopChoosing()

            if(isTargetMarked) {
                val link = createLink(draft)

                emitHandleLink(link)
            } else {
                hideLinking()
            }
        }
    }

    private fun abortLinking() {
        if(isSourceMarked) {
            stopChoosing()

            hideLinking()
        }
    }

    private fun hideLinking() {
        markedSource = draftsOf()
        markedTarget = draftsOf()

        emitHideSourceMark()
        emitHideTargetMark()
        emitHideSourceArrow()
    }

    private fun commenceChoosing(draft: AnyDraft) = chooser.commenceChoosing(draft, currentTouch)

    private fun continueChoosing(draft: AnyDraft) = chooser.continueChoosing(draft, currentTouch)

    private fun stopChoosing() = chooser.stopChoosing()

    private fun createLink(draft: AnyDraft) = Link(draft, markedSource, markedTarget)

    private fun emitShowSourceMark() = commands.emit(ShowSourceMark(markedSource))

    private fun emitShowTargetMark() = commands.emit(ShowTargetMark(markedTarget))

    private fun emitShowSourceArrow() = commands.emit(ShowSourceArrow(currentTouch))

    private fun emitHandleLink(link: Link) = commands.emit(HandleLink(link))

    private fun emitHideSourceMark() = commands.emit(HideSourceMark)

    private fun emitHideTargetMark() = commands.emit(HideTargetMark)

    private fun emitHideSourceArrow() = commands.emit(HideSourceArrow)
}