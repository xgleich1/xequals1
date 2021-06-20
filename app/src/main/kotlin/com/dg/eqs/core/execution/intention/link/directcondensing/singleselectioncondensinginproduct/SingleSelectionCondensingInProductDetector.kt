package com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.item.variable.Variable
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.ValidSingleSelectionCondensingMultiplication
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductEvent.InvalidSingleSelectionCondensingMultiplication


class SingleSelectionCondensingInProductDetector {
    fun detect(link: Link) = with(link) {
        when {
            isInvolvingNotZeroOrOneAndVariable() ||
            isInvolvingVariableAndNotZeroOrOne() ||
            isInvolvingVariableAndVariable() -> InvalidSingleSelectionCondensingMultiplication

            else -> ValidSingleSelectionCondensingMultiplication
        }
    }

    private fun Link.isInvolvingNotZeroOrOneAndVariable(): Boolean {
        if(firstTarget !is Variable) return false
        if(firstSource !is Value) return false

        return !firstSource.isZero && !firstSource.isOne
    }

    private fun Link.isInvolvingVariableAndNotZeroOrOne(): Boolean {
        if(firstSource !is Variable) return false
        if(firstTarget !is Value) return false

        return !firstTarget.isZero && !firstTarget.isOne
    }

    private fun Link.isInvolvingVariableAndVariable() =
            firstSource is Variable
         && firstTarget is Variable
}