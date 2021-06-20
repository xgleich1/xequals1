package com.dg.eqs.core.execution.intention.link.directcondensing

import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingEvent.*


class DirectCondensingDetector {
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

    private fun Link.detectForSingleSelection() = when(mutualParent) {
        is DashOperation -> SingleSelectionCondensingInDashOperation
        is Division -> SingleSelectionCondensingInDivision
        is Product -> SingleSelectionCondensingInProduct

        else -> throw IllegalArgumentException()
    }

    private fun Link.detectForMultiSelection() = when(mutualParent) {
        is DashOperation -> MultiSelectionCondensingInDashOperation
        is Product -> MultiSelectionCondensingInProduct

        else -> throw IllegalArgumentException()
    }
}