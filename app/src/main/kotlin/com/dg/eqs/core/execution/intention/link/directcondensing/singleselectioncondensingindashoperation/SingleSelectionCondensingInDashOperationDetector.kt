package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationEvent.*


class SingleSelectionCondensingInDashOperationDetector {
    private val Link.dashOperation get() = mutualParent as DashOperation


    fun detect(link: Link) = with(link) {
        if(isAddition()) {
            detectForAddition()
        } else {
            detectForSubtraction()
        }
    }

    private fun Link.isAddition() = dashOperation.isAddition(source, target)

    private fun Link.detectForAddition() = object : Detector() {
        override val validEvent = ValidSingleSelectionCondensingAddition
        override val invalidEvent = InvalidSingleSelectionCondensingAddition
    }.detect(this)

    private fun Link.detectForSubtraction() = object : Detector() {
        override val validEvent = ValidSingleSelectionCondensingSubtraction
        override val invalidEvent = InvalidSingleSelectionCondensingSubtraction
    }.detect(this)

    private abstract class Detector {
        abstract val validEvent: SingleSelectionCondensingInDashOperationEvent
        abstract val invalidEvent: SingleSelectionCondensingInDashOperationEvent


        fun detect(link: Link) = with(link) {
            when {
                isInvolvingNotZeroAndVariable() ||
                isInvolvingVariableAndNotZero() ||
                isInvolvingProductAndVariable() ||
                isInvolvingVariableAndProduct() ||
                isInvolvingNotZeroAndProduct() ||
                isInvolvingProductAndNotZero() ||
                isInvolvingUnlikelyVariables() ||
                isInvolvingUnlikelyProducts() -> invalidEvent

                else -> validEvent
            }
        }

        private fun Link.isInvolvingNotZeroAndVariable(): Boolean {
            if(firstTarget !is Variable) return false
            if(firstSource !is Value) return false

            return !firstSource.isZero
        }

        private fun Link.isInvolvingVariableAndNotZero(): Boolean {
            if(firstSource !is Variable) return false
            if(firstTarget !is Value) return false

            return !firstTarget.isZero
        }

        private fun Link.isInvolvingProductAndVariable() =
                firstSource is Product
             && firstTarget is Variable

        private fun Link.isInvolvingVariableAndProduct() =
                firstSource is Variable
             && firstTarget is Product

        private fun Link.isInvolvingNotZeroAndProduct(): Boolean {
            if(firstTarget !is Product) return false
            if(firstSource !is Value) return false

            return !firstSource.isZero
        }

        private fun Link.isInvolvingProductAndNotZero(): Boolean {
            if(firstSource !is Product) return false
            if(firstTarget !is Value) return false

            return !firstTarget.isZero
        }

        private fun Link.isInvolvingUnlikelyVariables(): Boolean {
            if(firstSource !is Variable) return false
            if(firstTarget !is Variable) return false

            return firstSource != firstTarget &&
                   firstSource != firstTarget.invert()
        }

        private fun Link.isInvolvingUnlikelyProducts(): Boolean {
            if(firstSource !is Product) return false
            if(firstTarget !is Product) return false

            return firstSource != firstTarget &&
                   firstSource != firstTarget.invert()
        }
    }
}