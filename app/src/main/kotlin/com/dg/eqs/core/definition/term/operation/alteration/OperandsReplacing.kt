package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


interface OperandsReplacing {
    fun replace(operands: Terms, old: Term, new: Term): Terms
}