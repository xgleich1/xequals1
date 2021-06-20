package com.dg.eqs.core.visualization.composition.horizontal.envelope

import com.dg.eqs.base.abbreviation.AnyTextSign
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.Operation
import com.dg.eqs.core.visualization.Draft
import com.dg.eqs.core.visualization.composition.horizontal.HorizontalComposite


abstract class HorizontalEnvelope<out T : Operation>(
        val inner: Draft<T>,
        val prefix: AnyTextSign? = null,
        val suffix: AnyTextSign? = null)
    : HorizontalComposite<T>(inner.origin, draftsOf(prefix, inner, suffix)) {

    override val baseline get() = inner.baseline
}