package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication

import com.dg.eqs.base.extension.find
import com.dg.eqs.base.extension.isDyad
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.definition.term.operation.dotoperation.product.Product
import com.dg.eqs.core.execution.intention.Intention.Link


class NumeratorMultiplicationDecider {
    private val Link.numerator get() =
        parentsChain.find<Division>().numerator


    fun decide(link: Link) = with(link) {
        isSpanningOverDivision() &&
        isInvolvingOutsideProduct() &&
        isInvolvingDivisionsNumerator()
    }

    private fun Link.isSpanningOverDivision() =
        parentsChain.isDyad<Product, Division>() ||
        parentsChain.isDyad<Division, Product>()

    private fun Link.isInvolvingOutsideProduct() =
        mutualParent is Product

    private fun Link.isInvolvingDivisionsNumerator() =
        isSourceTheNumerator() ||
        isTargetTheNumerator()

    private fun Link.isSourceTheNumerator() = numerator in source

    private fun Link.isTargetTheNumerator() = numerator in target
}