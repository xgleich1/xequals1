package com.dg.eqs.base.enveloping

import androidx.annotation.StringRes


class HtmlRes(@StringRes val resId: Int, vararg val args: Any) {
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(javaClass != other?.javaClass) return false

        other as HtmlRes

        if(resId != other.resId) return false
        if(!args.contentEquals(other.args)) return false

        return true
    }

    override fun hashCode() = 31 * resId + args.contentHashCode()

    override fun toString() = "HtmlRes($resId, ${args.contentToString()})"
}