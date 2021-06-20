package com.dg.eqs.core.definition

import com.dg.eqs.core.definition.term.Term


interface Origin<out T : Origin<T>> {
    fun isSolved(): Boolean

    fun remove(term: Term): T

    fun replace(old: Term, new: Term): T

    fun invert(): T

    fun copy(): T
}