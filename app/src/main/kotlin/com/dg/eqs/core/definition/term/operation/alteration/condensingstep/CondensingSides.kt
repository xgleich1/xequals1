package com.dg.eqs.core.definition.term.operation.alteration.condensingstep


data class CondensingSides<L : CondensingSide, R : CondensingSide>(val left: L, val right: R)