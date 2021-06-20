package com.dg.eqs.core.definition.term.operation

import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.fourth
import com.dg.eqs.base.extension.second
import com.dg.eqs.base.extension.third
import com.dg.eqs.core.definition.Parent
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.Terms
import com.dg.eqs.core.definition.term.operation.alteration.OperationAlteration
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException


abstract class Operation(val operands: Terms) : Term(), Parent {
    val first get() = operands.first
    val second get() = operands.second
    val third get() = operands.third
    val fourth get() = operands.fourth

    protected abstract val alteration: OperationAlteration


    init {
        check(operands.size >= 2)
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        if(!super.equals(other)) return false

        other as Operation

        if(operands != other.operands) return false

        return true
    }

    override fun hashCode() = 31 * super.hashCode() + operands.hashCode()

    override fun toString() = operands.toString()

    override fun isSolved() = resolve() == this

    override fun isConstant() = operands.allConstants()

    override fun remove(term: Term): Term {
        require(term !== this)

        return alteration.remove(this, term)
    }

    override fun replace(old: Term, new: Term): Term {
        if(old === this) return new

        return alteration.replace(this, old, new)
    }

    override fun copy() = recreate(operands.mapCopy())

    override fun resolve() = alteration.resolve(this)

    override fun any(predicate: (Term) -> Boolean) = operands.any(predicate)

    override fun forEach(action: (Term) -> Unit) = operands.forEach(action)

    abstract fun recreate(newOperands: Terms): Operation

    abstract fun applySign(): Operation

    operator fun component1() = first

    operator fun component2() = second

    operator fun component3() = third

    operator fun component4() = fourth

    operator fun contains(terms: Terms) = terms in operands

    fun find(predicate: (Term) -> Boolean) = operands.find(predicate)

    fun indexOf(operand: Term): Int {
        val index = operands.indexOf(operand)

        require(index != -1)

        return index
    }

    @Throws(DivisionThroughZeroException::class)
    fun condense() = alteration.condense(this)

    @Throws(DivisionThroughZeroException::class)
    fun condense(source: Terms, target: Terms): Term {
        require(source in operands)
        require(target in operands)

        return alteration.condense(this, source, target)
    }
}