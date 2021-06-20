package com.dg.eqs.core.execution.intention.link

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.LinkIntentionEvent.*
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingExecutor


class LinkIntentionExecutor(
        private val linkIntentionDetector: LinkIntentionDetector,
        private val directShiftingExecutor: DirectShiftingExecutor,
        private val indirectShiftingExecutor: IndirectShiftingExecutor,
        private val directCondensingExecutor: DirectCondensingExecutor,
        private val indirectCondensingExecutor: IndirectCondensingExecutor) {

    fun execute(link: Link) = when(linkIntentionDetector.detect(link)) {
        DirectShifting -> directShiftingExecutor.execute(link)
        IndirectShifting -> indirectShiftingExecutor.execute(link)
        DirectCondensing -> directCondensingExecutor.execute(link)
        IndirectCondensing -> indirectCondensingExecutor.execute(link)
    }
}