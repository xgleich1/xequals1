package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce

import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceEvent.*


class CondensingReduceDetector {
    fun detect(link: Link) = with(link) {
        if(isInvolvingOnlyTwoTerms()) {
            detectForSingleSelection()
        } else {
            detectForMultiSelection()
        }
    }

    private fun Link.isInvolvingOnlyTwoTerms() =
            source.isSingle
         && target.isSingle

    private fun Link.detectForSingleSelection() = object : Detector() {
        override val sourceInDenominatorEvent = SingleSelectionReduceWithSourceInDenominator
        override val targetInDenominatorEvent = SingleSelectionReduceWithTargetInDenominator
    }.detect(this)

    private fun Link.detectForMultiSelection() = object : Detector() {
        override val sourceInDenominatorEvent = MultiSelectionReduceWithSourceInDenominator
        override val targetInDenominatorEvent = MultiSelectionReduceWithTargetInDenominator
    }.detect(this)

    private abstract class Detector {
        abstract val sourceInDenominatorEvent: CondensingReduceEvent
        abstract val targetInDenominatorEvent: CondensingReduceEvent

        private val Link.denominator get() =
            (mutualParent as Division).denominator


        fun detect(link: Link) = with(link) {
            if(isSourceInDenominator()) {
                sourceInDenominatorEvent
            } else {
                targetInDenominatorEvent
            }
        }

        private fun Link.isSourceInDenominator() =
                denominator in source || sourceParent === denominator
    }
}