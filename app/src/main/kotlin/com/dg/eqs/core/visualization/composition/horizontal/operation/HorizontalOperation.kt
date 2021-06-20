package com.dg.eqs.core.visualization.composition.horizontal.operation

import com.dg.eqs.base.abbreviation.AnyTextSigns
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.base.extension.mix
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.composition.horizontal.HorizontalComposite


abstract class HorizontalOperation<out T : Operation>(
        origin: T,
        val operands: TermDrafts,
        val signs: AnyTextSigns)
    : HorizontalComposite<T>(origin, draftsOf(operands.mix(signs))) {

    override val baseline get() = operands.last().baseline


    init {
        operands.forEachIndexed { index, operand ->
            operand.choosableSuccessor = operands.getOrNull(index + 1)
            operand.choosablePredecessor = operands.getOrNull(index - 1)
        }
    }
}