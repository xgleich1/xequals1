package com.dg.eqs.base.enveloping

import androidx.annotation.StringRes


class StringRes(@StringRes val resId: Int, vararg val args: Any) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as com.dg.eqs.base.enveloping.StringRes

        if(resId != other.resId) return false
        if(!args.contentEquals(other.args)) return false

        return true
    }

    override fun hashCode() = 31 * resId + args.contentHashCode()

    override fun toString() = "StringRes($resId, ${args.contentToString()})"
}