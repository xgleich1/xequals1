package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import com.dg.eqs.core.definition.term.Terms


interface CondensingMatcher {
    fun matches(operands: Terms): Boolean

    fun matches(left: Terms, right: Terms): Boolean
}