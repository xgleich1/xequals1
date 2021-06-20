package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms


class DefaultOperandsReplacing : OperandsReplacing {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode() = javaClass.hashCode()

    override fun replace(operands: Terms, old: Term, new: Term) =
            if(old in operands) {
                operands.replace(old, new)
            } else {
                operands.mapReplace(old, new)
            }
}