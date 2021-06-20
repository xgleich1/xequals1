package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.InvalidSingleSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.ValidSingleSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.InvalidSingleSelectionNumeratorMultiplication
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationEvent.ValidSingleSelectionNumeratorMultiplication


class SingleSelectionNumeratorMultiplicationDetector(
        private val singleSelectionCondensingInProductDetector: SingleSelectionCondensingInProductDetector) {

    fun detect(left: Term, right: Term): SingleSelectionNumeratorMultiplicationEvent {
        val product = PositiveProduct(left, right)
        val condensingLink = Link(product, left, right)

        return when(singleSelectionCondensingInProductDetector.detect(condensingLink)) {
            ValidSingleSelectionCondensingMultiplication -> ValidSingleSelectionNumeratorMultiplication
            InvalidSingleSelectionCondensingMultiplication -> InvalidSingleSelectionNumeratorMultiplication
        }
    }
}