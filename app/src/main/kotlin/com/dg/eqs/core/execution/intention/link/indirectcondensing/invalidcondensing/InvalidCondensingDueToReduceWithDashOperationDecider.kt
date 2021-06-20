package com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing

import com.dg.eqs.base.extension.isDyad
import com.dg.eqs.base.extension.isTriad
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link


class InvalidCondensingDueToReduceWithDashOperationDecider {
    fun decide(link: Link) = with(link) {
        isSpanningOverDivision() &&
        isNotExtractableAsDivision()
    }

    private fun Link.isSpanningOverDivision() =
            mutualParent is Division

    private fun Link.isNotExtractableAsDivision() =
            with(parentsChain) {
                isDyad<DashOperation, Division>() ||
                isDyad<Division, DashOperation>() ||
                isTriad<DashOperation, Division, Product>() ||
                isTriad<Product, Division, DashOperation>() ||
                isTriad<DashOperation, Division, DashOperation>()
            }
}