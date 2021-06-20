package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class DefaultOperandsRemoving : OperandsRemoving {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode() = javaClass.hashCode()

    override fun remove(operands: Terms, term: Term) =
            if(term in operands) {
                operands.remove(term)
            } else {
                operands.mapRemove(term)
            }
}