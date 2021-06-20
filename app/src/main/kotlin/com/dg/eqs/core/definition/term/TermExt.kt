package com.dg.eqs.core.definition.term

import com.dg.eqs.core.definition.term.item.value.Value
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division


val Term.isOne get() = this is Value && isOne

val Term.isZero get() = this is Value && isZero

val Term.isDivisionThroughPositiveOne get() = this is Division && isDivisionThroughPositiveOne