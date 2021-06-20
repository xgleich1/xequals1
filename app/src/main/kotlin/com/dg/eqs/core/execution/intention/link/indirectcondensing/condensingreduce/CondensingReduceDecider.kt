package com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce

import com.dg.eqs.base.extension.isDyad
import com.dg.eqs.base.extension.isTriad
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link


class CondensingReduceDecider {
    fun decide(link: Link) = with(link) {
        isSpanningOverDivision() &&
        isExtractableAsDivision()
    }

    private fun Link.isSpanningOverDivision() =
            mutualParent is Division

    private fun Link.isExtractableAsDivision() =
            with(parentsChain) {
                isDyad<Product, Division>() ||
                isDyad<Division, Product>() ||
                isTriad<Product, Division, Product>()
            }
}