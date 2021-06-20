package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDivisionThroughZeroInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.valid.ReduceInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.Step.ValidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceEvent.*


class SingleSelectionReduceWithSourceInDenominatorExecutor(
        private val detector: SingleSelectionReduceWithSourceInDenominatorDetector,
        private val calculator: SingleSelectionReduceWithSourceInDenominatorCalculator) {

    fun execute(link: Link) = when(detector.detect(link)) {
        ValidSingleSelectionReduce -> ValidStep(ReduceInfo, calculator.calculate(link))
        InvalidSingleSelectionReduceThroughZero -> InvalidStep(InvalidCondensingDivisionThroughZeroInfo, link.origin)
        InvalidSingleSelectionReduce -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}