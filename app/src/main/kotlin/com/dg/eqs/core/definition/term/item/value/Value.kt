package com.dg.eqs.core.definition.term.item.value

import com.dg.eqs.core.definition.term.item.Item


abstract class Value(val unsignedNumber: Int) : Item() {
    val signedNumber get() = if(isNegative) -unsignedNumber else unsignedNumber

    val isOne = unsignedNumber == 1
    val isZero = unsignedNumber == 0


    init {
        require(unsignedNumber >= 0)
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        if(!super.equals(other)) return false

        other as Value

        if(unsignedNumber != other.unsignedNumber) return false

        return true
    }

    override fun hashCode() = 31 * super.hashCode() + unsignedNumber

    override fun toString() = "${if(isNegative) "-" else ""}$unsignedNumber"

    override fun isConstant() = true
}