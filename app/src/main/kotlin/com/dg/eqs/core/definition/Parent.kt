package com.dg.eqs.core.definition

import com.dg.eqs.core.definition.term.Term


interface Parent {
    fun any(predicate: (Term) -> Boolean): Boolean

    fun forEach(action: (Term) -> Unit)
}