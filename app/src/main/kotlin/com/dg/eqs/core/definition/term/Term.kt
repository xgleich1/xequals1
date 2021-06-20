package com.dg.eqs.core.definition.term

import com.dg.eqs.core.definition.Origin
import com.dg.eqs.core.definition.term.operation.alteration.condensingstep.CondensingSide
import com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration.DivisionThroughZeroException


abstract class Term : Origin<Term>, CondensingSide {
    abstract val isNegative: Boolean

    val isPositive get() = !isNegative


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Term

        if(isNegative != other.isNegative) return false

        return true
    }

    override fun hashCode() = isNegative.hashCode()

    abstract fun isConstant(): Boolean

    @Throws(DivisionThroughZeroException::class)
    abstract fun resolve(): Term
}