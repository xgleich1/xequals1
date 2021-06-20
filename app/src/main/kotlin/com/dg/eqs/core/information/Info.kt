package com.dg.eqs.core.information

import com.dg.eqs.base.enveloping.LayoutRes


abstract class Info {
    abstract val content: LayoutRes
    abstract val isVital: Boolean
    abstract val isValid: Boolean


    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as Info

        if(content != other.content) return false
        if(isVital != other.isVital) return false
        if(isValid != other.isValid) return false

        return true
    }

    override fun hashCode(): Int {
        var result = content.hashCode()

        result = 31 * result + isVital.hashCode()
        result = 31 * result + isValid.hashCode()

        return result
    }

    override fun toString(): String = javaClass.simpleName
}