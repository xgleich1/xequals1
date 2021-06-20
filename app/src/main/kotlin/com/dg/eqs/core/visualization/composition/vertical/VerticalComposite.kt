package com.dg.eqs.core.visualization.composition.vertical

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyDrafts
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.visualization.composition.CompositeDraft


abstract class VerticalComposite<out T : AnyOrigin>(origin: T, parts: AnyDrafts)
    : CompositeDraft<T>(origin, parts) {

    override fun calculatePartShiftX(base: AnyDraft, part: AnyDraft) = base.centerX - part.centerX

    override fun calculatePartShiftY(base: AnyDraft, part: AnyDraft) = base.finalY - part.firstY
}