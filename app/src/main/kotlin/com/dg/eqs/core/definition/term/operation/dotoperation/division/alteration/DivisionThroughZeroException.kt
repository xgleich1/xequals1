package com.dg.eqs.core.definition.term.operation.dotoperation.division.alteration

import com.dg.eqs.core.definition.term.Term


class DivisionThroughZeroException(numerator: Term)
    : Exception("Cannot divide $numerator through zero")