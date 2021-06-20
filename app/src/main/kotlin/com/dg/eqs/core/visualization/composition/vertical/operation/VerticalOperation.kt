package com.dg.eqs.core.visualization.composition.vertical.operation

import com.dg.eqs.base.abbreviation.AnyLineSign
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.composition.vertical.VerticalComposite


abstract class VerticalOperation<out T : Operation>(
        origin: T,
        val top: TermDraft,
        val bottom: TermDraft,
        val sign: AnyLineSign)
    : VerticalComposite<T>(origin, draftsOf(top, sign, bottom)) {

    override val baseline get() = sign.baseline


    init {
        top.choosableSuccessor = bottom
        bottom.choosablePredecessor = top
    }
}