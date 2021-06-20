package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.alteration.DefaultOperandsCondensing
import com.dg.eqs.core.definition.term.operation.alteration.OperandsCondensing


class DivisionOperandsCondensing(
        private val defaultCondensing: DefaultOperandsCondensing)
    : OperandsCondensing {

    constructor(vararg steps: AnyCondensingStep)
            : this(DefaultOperandsCondensing(*steps))

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as DivisionOperandsCondensing

        if(defaultCondensing != other.defaultCondensing) return false

        return true
    }

    override fun hashCode() = defaultCondensing.hashCode()

    override fun condense(operands: Terms): Terms {
        requireNoDivisionThroughZero(operands)

        return defaultCondensing.condense(operands)
    }

    override fun condense(operands: Terms, source: Terms, target: Terms): Terms {
        requireNoDivisionThroughZero(operands)

        return defaultCondensing.condense(operands, source, target)
    }

    private fun requireNoDivisionThroughZero(operands: Terms) {
        val (numerator, denominator) = operands

        if(denominator.isZero) {
            throw DivisionThroughZeroException(numerator)
        }
    }
}