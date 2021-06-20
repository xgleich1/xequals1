package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


interface OperandsRemoving {
    fun remove(operands: Terms, term: Term): Terms
}