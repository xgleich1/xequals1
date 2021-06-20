package com.dg.eqs.core.visualization.composition.horizontal.relation

import com.dg.eqs.base.abbreviation.AnyTextSign
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.visualization.composition.horizontal.HorizontalComposite


abstract class HorizontalRelation<out T : Relation>(
        origin: T,
        val left: TermDraft,
        val right: TermDraft,
        val sign: AnyTextSign)
    : HorizontalComposite<T>(origin, draftsOf(left, sign, right)) {

    override val baseline get() = sign.baseline
}