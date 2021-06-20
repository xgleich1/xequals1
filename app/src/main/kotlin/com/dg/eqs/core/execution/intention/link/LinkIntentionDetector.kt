package com.dg.eqs.core.execution.intention.link

import com.dg.eqs.base.extension.first
import com.dg.eqs.base.extension.second
import com.dg.eqs.core.definition.relation.Relation
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.LinkIntentionEvent.*


class LinkIntentionDetector {
    fun detect(link: Link) = with(link) {
        if(isBridgingRelationSign()) {
            detectShiftingIntention()
        } else {
            detectCondensingIntention()
        }
    }

    private fun Link.isBridgingRelationSign() = mutualParent is Relation

    private fun Link.detectShiftingIntention() =
            if(isCalculableWithRelation()) DirectShifting else IndirectShifting

    private fun Link.detectCondensingIntention() =
            if(isCalculableWithOperation()) DirectCondensing else IndirectCondensing

    private fun Link.isCalculableWithRelation() =
            parentsChain.first is Relation || parentsChain.second is Relation

    private fun Link.isCalculableWithOperation() = sourceParent === targetParent
}