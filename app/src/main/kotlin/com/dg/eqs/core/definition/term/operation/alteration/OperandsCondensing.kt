package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException


interface OperandsCondensing {
    @Throws(DivisionThroughZeroException::class)
    fun condense(operands: Terms): Terms

    @Throws(DivisionThroughZeroException::class)
    fun condense(operands: Terms, source: Terms, target: Terms): Terms
}