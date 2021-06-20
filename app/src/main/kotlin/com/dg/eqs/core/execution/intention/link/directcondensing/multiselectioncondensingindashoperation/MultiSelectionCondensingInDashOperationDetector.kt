package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationEvent.*


class MultiSelectionCondensingInDashOperationDetector {
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
        override val validEvent = ValidMultiSelectionCondensingAddition
        override val invalidEvent = InvalidMultiSelectionCondensingAddition
    }.detect(this)

    private fun Link.detectForSubtraction() = object : Detector() {
        override val validEvent = ValidMultiSelectionCondensingSubtraction
        override val invalidEvent = InvalidMultiSelectionCondensingSubtraction
    }.detect(this)

    private abstract class Detector {
        abstract val validEvent: MultiSelectionCondensingInDashOperationEvent
        abstract val invalidEvent: MultiSelectionCondensingInDashOperationEvent


        fun detect(link: Link) = with(link) {
            when {
                isInvolvingNoZerosAndSingleValueOtherThanZero() ||
                isInvolvingSingleValueOtherThanZeroAndNoZeros() ||
                isInvolvingNoZerosAndSingleVariable() ||
                isInvolvingSingleVariableAndNoZeros() ||
                isInvolvingNoZerosAndSingleProduct() ||
                isInvolvingSingleProductAndNoZeros() ||
                isInvolvingNoZerosAndNoZeros() -> invalidEvent

                else -> validEvent
            }
        }

        private fun Link.isInvolvingNoZerosAndSingleValueOtherThanZero(): Boolean {
            if(!target.isMonad<Value>()) return false
            if(firstTarget.isZero) return false

            return !source.allZeros()
        }

        private fun Link.isInvolvingSingleValueOtherThanZeroAndNoZeros(): Boolean {
            if(!source.isMonad<Value>()) return false
            if(firstSource.isZero) return false

            return !target.allZeros()
        }

        private fun Link.isInvolvingNoZerosAndSingleVariable(): Boolean {
            if(!target.isMonad<Variable>()) return false

            return !source.allZeros()
        }

        private fun Link.isInvolvingSingleVariableAndNoZeros(): Boolean {
            if(!source.isMonad<Variable>()) return false

            return !target.allZeros()
        }

        private fun Link.isInvolvingNoZerosAndSingleProduct(): Boolean {
            if(!target.isMonad<Product>()) return false

            return !source.allZeros()
        }

        private fun Link.isInvolvingSingleProductAndNoZeros(): Boolean {
            if(!source.isMonad<Product>()) return false

            return !target.allZeros()
        }

        private fun Link.isInvolvingNoZerosAndNoZeros(): Boolean {
            if(source.isSingle) return false
            if(target.isSingle) return false

            return !source.allZeros() && !target.allZeros()
        }
    }
}