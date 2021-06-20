package com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.operation.dotoperation.product.PositiveProduct
import com.dg.eqs.core.execution.intention.Intention.Link


class SingleSelectionNumeratorMultiplicationCalculator {
    fun calculate(link: Link, left: Term, right: Term) = with(link) {
        val condensation = PositiveProduct(left, right).condense()

        origin.remove(firstSource).replace(firstTarget, condensation)
    }
}