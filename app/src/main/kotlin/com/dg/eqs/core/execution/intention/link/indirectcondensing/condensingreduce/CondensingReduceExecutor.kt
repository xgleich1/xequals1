package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator.SingleSelectionReduceWithSourceInDenominatorExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator.SingleSelectionReduceWithTargetInDenominatorExecutor


class CondensingReduceExecutor(
        private val condensingReduceDetector: CondensingReduceDetector,
        private val singleSelectionReduceWithSourceInDenominatorExecutor: SingleSelectionReduceWithSourceInDenominatorExecutor,
        private val singleSelectionReduceWithTargetInDenominatorExecutor: SingleSelectionReduceWithTargetInDenominatorExecutor) {

    fun execute(link: Link) = when(condensingReduceDetector.detect(link)) {
        SingleSelectionReduceWithSourceInDenominator -> singleSelectionReduceWithSourceInDenominatorExecutor.execute(link)
        SingleSelectionReduceWithTargetInDenominator -> singleSelectionReduceWithTargetInDenominatorExecutor.execute(link)
        MultiSelectionReduceWithSourceInDenominator -> InvalidStep(InvalidCondensingInfo, link.origin)
        MultiSelectionReduceWithTargetInDenominator -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}