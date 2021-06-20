package com.dg.eqs.core.definition.term.operation.alteration.condensingstep

import com.dg.eqs.core.definition.term.Terms


interface CondensingExtractor {
    fun extract(operands: Terms): CondensingSides<Terms, Terms>
}