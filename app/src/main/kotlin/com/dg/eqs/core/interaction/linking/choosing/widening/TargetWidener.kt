package com.dg.eqs.core.interaction.linking.choosing.widening

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.interaction.linking.choosing.Choice


class TargetWidener(private val delayer: WideningDelayer) {
    fun start(target: Choice, source: Choice) {
        delayer.delay {
            widen(target, source)
            start(target, source)
        }
    }

    fun stop() = delayer.cancel()

    private fun widen(target: Choice, source: Choice) {
        widenWithNewChoosable(target, source)
        widenWithFilledParent(target, source)
    }

    private fun widenWithNewChoosable(target: Choice, source: Choice) {
        onNewChoosable(target, source) {
            target.addEnsuingChosen(it)
        }
    }

    private fun widenWithFilledParent(target: Choice, source: Choice) {
        onNewChoosable(target, source) {
            if(it === target.choosableParent) {
                target.addEnsuingChosen(it)
            }
        }
    }

    private fun onNewChoosable(target: Choice, source: Choice, on: (TermDraft) -> Unit) {
        with(target) {
            choosablePredecessor?.let {
                if(isFreeFromChosenSource(it, source)) return on(it)
            }

            choosableSuccessor?.let {
                if(isFreeFromChosenSource(it, source)) return on(it)
            }

            choosableParent?.let {
                if(isFreeFromChosenSource(it, source)) return on(it)
            }
        }
    }

    private fun isFreeFromChosenSource(part: TermDraft, source: Choice) = source absentIn part
}