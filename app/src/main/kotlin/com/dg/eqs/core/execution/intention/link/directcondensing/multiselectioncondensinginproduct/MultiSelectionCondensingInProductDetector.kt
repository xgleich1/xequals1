package com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct

import com.dg.eqs.base.extension.isMonad
import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isOne
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.InvalidMultiSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductEvent.ValidMultiSelectionCondensingMultiplication


class MultiSelectionCondensingInProductDetector {
    fun detect(link: Link) = with(link) {
        when {
            isInvolvingNoZerosOrOnesAndSingleValueOtherThanZeroOrOne() ||
            isInvolvingSingleValueOtherThanZeroOrOneAndNoZerosOrOnes() ||
            isInvolvingNoZerosOrOnesAndSingleVariable() ||
            isInvolvingSingleVariableAndNoZerosOrOnes() ||
            isInvolvingNoZerosOrOnesAndNoZerosOrOnes() -> InvalidMultiSelectionCondensingMultiplication

            else -> ValidMultiSelectionCondensingMultiplication
        }
    }

    private fun Link.isInvolvingNoZerosOrOnesAndSingleValueOtherThanZeroOrOne(): Boolean {
        if(!target.isMonad<Value>()) return false
        if(firstTarget.isZero) return false
        if(firstTarget.isOne) return false

        return !source.allZerosOrOnes()
    }

    private fun Link.isInvolvingSingleValueOtherThanZeroOrOneAndNoZerosOrOnes(): Boolean {
        if(!source.isMonad<Value>()) return false
        if(firstSource.isZero) return false
        if(firstSource.isOne) return false

        return !target.allZerosOrOnes()
    }

    private fun Link.isInvolvingNoZerosOrOnesAndSingleVariable(): Boolean {
        if(!target.isMonad<Variable>()) return false

        return !source.allZerosOrOnes()
    }

    private fun Link.isInvolvingSingleVariableAndNoZerosOrOnes(): Boolean {
        if(!source.isMonad<Variable>()) return false

        return !target.allZerosOrOnes()
    }

    private fun Link.isInvolvingNoZerosOrOnesAndNoZerosOrOnes(): Boolean {
        if(source.isSingle) return false
        if(target.isSingle) return false

        return !source.allZerosOrOnes() && !target.allZerosOrOnes()
    }

    private fun Terms.allZerosOrOnes() = allZeros() || allOnes()
}