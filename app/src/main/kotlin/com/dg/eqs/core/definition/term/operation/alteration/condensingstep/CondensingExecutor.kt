package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import com.dg.eqs.core.definition.term.Terms


interface CondensingExecutor<in L : CondensingSide, in R : CondensingSide> {
    fun execute(left: L, right: R): Terms
}