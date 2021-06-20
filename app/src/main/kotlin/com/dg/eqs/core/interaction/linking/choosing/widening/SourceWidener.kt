package com.dg.eqs.core.interaction.linking.choosing.widening

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.linking.choosing.Choice


class SourceWidener(private val delayer: WideningDelayer) {
    fun start(source: Choice) {
        delayer.delay {
            widen(source)
            start(source)
        }
    }

    fun stop() = delayer.cancel()

    private fun widen(source: Choice) {
        widenWithNewChoosable(source)
        widenWithFilledParent(source)
    }

    private fun widenWithNewChoosable(source: Choice) {
        onNewChoosable(source) {
            source.addEnsuingChosen(it)
        }
    }

    private fun widenWithFilledParent(source: Choice) {
        onNewChoosable(source) {
            if(it === source.choosableParent) {
                source.addEnsuingChosen(it)
            }
        }
    }

    private fun onNewChoosable(source: Choice, on: (TermDraft) -> Unit) {
        with(source) {
            choosablePredecessor?.let {
                if(!isDraftsRemainingUnchosenLeftmostPart(it, source)) return on(it)
            }

            choosableSuccessor?.let {
                if(!isDraftsRemainingUnchosenRightmostPart(it, source)) return on(it)
            }

            choosableParent?.let {
                if(!isActualDraft(it)) return on(it)
            }
        }
    }

    private fun isDraftsRemainingUnchosenLeftmostPart(part: TermDraft, source: Choice) =
           isInsideDraft(part)
        && isLeftmostPart(part)
        && allRightPartsAreChosen(source)

    private fun isDraftsRemainingUnchosenRightmostPart(part: TermDraft, source: Choice) =
           isInsideDraft(part)
        && isRightmostPart(part)
        && allLeftPartsAreChosen(source)

    private fun isActualDraft(part: TermDraft) = part.choosableParent === null

    private fun isInsideDraft(part: TermDraft) = part.choosableParent?.choosableParent === null

    private fun isLeftmostPart(part: TermDraft) = part.choosablePredecessor === null

    private fun isRightmostPart(part: TermDraft) = part.choosableSuccessor === null

    private fun allRightPartsAreChosen(source: Choice) = source.choosableSuccessor === null

    private fun allLeftPartsAreChosen(source: Choice) = source.choosablePredecessor === null
}