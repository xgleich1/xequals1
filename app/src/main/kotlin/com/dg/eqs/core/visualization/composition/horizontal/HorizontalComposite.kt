package com.dg.eqs.core.visualization.composition.horizontal

import com.dg.eqs.base.abbreviation.AnyDraft
import com.dg.eqs.base.abbreviation.AnyDrafts
import com.dg.eqs.base.abbreviation.AnyOrigin
import com.dg.eqs.core.visualization.composition.CompositeDraft


abstract class HorizontalComposite<out T : AnyOrigin>(origin: T, parts: AnyDrafts)
    : CompositeDraft<T>(origin, parts) {

    override fun calculatePartShiftX(base: AnyDraft, part: AnyDraft) = base.finalX - part.firstX

    override fun calculatePartShiftY(base: AnyDraft, part: AnyDraft) = base.baseline - part.baseline
}