package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication

import com.dg.eqs.base.extension.find
import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationEvent.*


class NumeratorMultiplicationDetector {
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
        override val sourceLeftEvent = SingleSelectionNumeratorMultiplicationWithSourceLeft
        override val targetLeftEvent = SingleSelectionNumeratorMultiplicationWithTargetLeft
    }.detect(this)

    private fun Link.detectForMultiSelection() = object : Detector() {
        override val sourceLeftEvent = MultiSelectionNumeratorMultiplicationWithSourceLeft
        override val targetLeftEvent = MultiSelectionNumeratorMultiplicationWithTargetLeft
    }.detect(this)

    private abstract class Detector {
        abstract val sourceLeftEvent: NumeratorMultiplicationEvent
        abstract val targetLeftEvent: NumeratorMultiplicationEvent

        private val Link.product get() = parentsChain.find<Product>()
        private val Link.division get() = parentsChain.find<Division>()


        fun detect(link: Link) = with(link) {
            if(isSourceLeft()) {
                sourceLeftEvent
            } else {
                targetLeftEvent
            }
        }

        private fun Link.isSourceLeft() = if(sourceParent is Product) {
            product.indexOf(source.first) < product.indexOf(division)
        } else {
            product.indexOf(division) < product.indexOf(target.first)
        }
    }
}