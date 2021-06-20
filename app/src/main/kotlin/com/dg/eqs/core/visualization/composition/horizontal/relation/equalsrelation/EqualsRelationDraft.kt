package com.dg.eqs.core.visualization.composition.horizontal.relation.equalsrelation

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.relation.equalsrelation.EqualsRelation
import com.dg.eqs.core.visualization.Pencil
import com.dg.eqs.core.visualization.composition.horizontal.relation.HorizontalRelation
import com.dg.eqs.core.visualization.symbolization.text.sign.EqualsSign


class EqualsRelationDraft(
        origin: EqualsRelation,
        left: TermDraft,
        right: TermDraft,
        sign: EqualsSign = EqualsSign(origin))
    : HorizontalRelation<EqualsRelation>(origin, left, right, sign) {

    override fun specifyPaddingLeft(pencil: Pencil) = 0

    override fun specifyPaddingRight(pencil: Pencil) = 0

    override fun calculatePartWidth(part: AnyDraft) = part.width

    override fun calculatePartHeight(part: AnyDraft) = part.height
}