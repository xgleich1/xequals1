package com.dg.eqs.core.definition.term.item.variable

import com.dg.eqs.core.definition.term.item.Item


abstract class Variable(val unsignedName: String) : Item() {
    init {
        with(unsignedName) {
            require(!startsWith("+"))
            require(!startsWith("-"))
        }
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false
        if(!super.equals(other)) return false

        other as Variable

        if(unsignedName != other.unsignedName) return false

        return true
    }

    override fun hashCode() = 31 * super.hashCode() + unsignedName.hashCode()

    override fun toString() = "${if(isNegative) "-" else ""}$unsignedName"

    override fun isConstant() = false
}