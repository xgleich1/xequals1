package com.dg.eqs.core.execution.intention.link.indirectcondensing


enum class IndirectCondensingEvent {
    CondensingReduce,
    NumeratorMultiplication,
    InvalidIndirectCondensingDueToReduceWithDashOperation,
    InvalidIndirectCondensingDueToOrderOfOperations,
    InvalidIndirectCondensingDueToBracketing,
    InvalidIndirectCondensing,
}