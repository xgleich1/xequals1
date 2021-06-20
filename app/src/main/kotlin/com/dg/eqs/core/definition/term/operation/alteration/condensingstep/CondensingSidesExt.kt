package com.dg.eqs.core.definition.term.operation.alteration.condensingstep


infix fun <L : CondensingSide, R : CondensingSide> L.and(right: R) = CondensingSides(this, right)