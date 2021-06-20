package com.dg.eqs.core.execution.intention.link.directcondensing

import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.execution.intention.Intention.Link


class DirectCondensingCalculator {
    private val Link.operation
        get() = mutualParent as Operation


    fun calculate(link: Link) = with(link) {
        val condensation = operation.condense(source, target)

        origin.replace(operation, condensation)
    }
}