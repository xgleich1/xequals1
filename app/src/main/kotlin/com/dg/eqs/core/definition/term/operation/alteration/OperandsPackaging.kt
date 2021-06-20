package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation


interface OperandsPackaging {
    fun pack(origin: Operation, newOperands: Terms): Term
}