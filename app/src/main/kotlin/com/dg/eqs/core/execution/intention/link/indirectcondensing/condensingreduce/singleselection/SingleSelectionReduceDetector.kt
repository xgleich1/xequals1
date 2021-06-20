package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.division.PositiveDivision
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceEvent.*


class SingleSelectionReduceDetector(
        private val singleSelectionCondensingInDivisionDetector: SingleSelectionCondensingInDivisionDetector) {

    fun detect(numerator: Term, denominator: Term): SingleSelectionReduceEvent {
        val division = PositiveDivision(numerator, denominator)
        val condensingLink = Link(division, numerator, denominator)

        return when(singleSelectionCondensingInDivisionDetector.detect(condensingLink)) {
            ValidSingleSelectionCondensingDivision -> ValidSingleSelectionReduce
            InvalidSingleSelectionCondensingDivisionThroughZero -> InvalidSingleSelectionReduceThroughZero
            InvalidSingleSelectionCondensingDivision -> InvalidSingleSelectionReduce
        }
    }
}