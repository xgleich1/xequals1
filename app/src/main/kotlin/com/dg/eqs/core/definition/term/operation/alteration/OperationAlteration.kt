package com.dg.eqs.core.definition.term.operation.alteration

import com.dg.eqs.base.abbreviation.AnyCondensingStep
import com.dg.eqs.base.extension.isSingle
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException


class OperationAlteration(
        private val removing: OperandsRemoving,
        private val replacing: OperandsReplacing,
        private val condensing: OperandsCondensing,
        private val packaging: OperandsPackaging) {

    constructor(vararg steps: AnyCondensingStep) : this(
            DefaultOperandsRemoving(),
            DefaultOperandsReplacing(),
            DefaultOperandsCondensing(*steps),
            DefaultOperandsPackaging())

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as OperationAlteration

        if(removing != other.removing) return false
        if(replacing != other.replacing) return false
        if(condensing != other.condensing) return false
        if(packaging != other.packaging) return false

        return true
    }

    override fun hashCode(): Int {
        var result = removing.hashCode()

        result = 31 * result + replacing.hashCode()
        result = 31 * result + condensing.hashCode()
        result = 31 * result + packaging.hashCode()

        return result
    }

    fun remove(operation: Operation, term: Term) = with(operation) {
        pack(newOperands = removing.remove(operands, term))
    }

    fun replace(operation: Operation, old: Term, new: Term) = with(operation) {
        pack(newOperands = replacing.replace(operands, old, new))
    }

    @Throws(DivisionThroughZeroException::class)
    fun resolve(operation: Operation) = with(operation) {
        var resolvingResult: Terms

        var condensingResult = operands

        do {
            resolvingResult = condensingResult
                    .mapResolve()

            if(resolvingResult.isSingle) break

            condensingResult = condensing
                    .condense(resolvingResult)

        } while(condensingResult != resolvingResult)

        pack(newOperands = resolvingResult)
    }

    @Throws(DivisionThroughZeroException::class)
    fun condense(operation: Operation) = with(operation) {
        pack(newOperands = condensing.condense(operands))
    }

    @Throws(DivisionThroughZeroException::class)
    fun condense(operation: Operation, source: Terms, target: Terms) = with(operation) {
        pack(newOperands = condensing.condense(operands, source, target))
    }

    private fun Operation.pack(newOperands: Terms) = packaging.pack(this, newOperands)
}