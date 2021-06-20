package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.base.extension.contentEqualsByIdentity
import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.base.extension.single
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation


class DefaultOperandsPackaging : OperandsPackaging {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        return true
    }

    override fun hashCode() = javaClass.hashCode()

    override fun pack(origin: Operation, newOperands: Terms) =
            if(newOperands.isSingle) {
                origin.unpack(newOperands)
            } else {
                origin.repack(newOperands)
            }

    private fun Operation.unpack(newOperands: Terms): Term {
        val newOperand = newOperands.single

        return if(isPositive) newOperand else newOperand.invert()
    }

    private fun Operation.repack(newOperands: Terms): Operation {
        val operandsNotAltered = newOperands
                .contentEqualsByIdentity(operands)

        return if(operandsNotAltered) this else recreate(newOperands)
    }
}