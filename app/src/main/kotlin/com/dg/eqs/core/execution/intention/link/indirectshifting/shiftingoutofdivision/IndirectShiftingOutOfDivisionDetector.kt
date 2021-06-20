package com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision

import com.dg.eqs.base.extension.find
import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionEvent.*


class IndirectShiftingOutOfDivisionDetector {
    private val Link.division
        get() = parentsChain.find<Division>()


    fun detect(link: Link) = with(link) {
        if(isShiftingDivision()) {
            detectForShiftingDivision()
        } else {
            detectForShiftingMultiplication()
        }
    }

    private fun Link.isShiftingDivision() =
            sourceParent === division.numerator

    private fun Link.detectForShiftingDivision() = object : Detector() {
        override val validEvent = ValidIndirectShiftingDivisionOutOfDivision
        override val invalidEvent = InvalidIndirectShiftingDivisionOfZeroOutOfDivision
    }.detect(this)

    private fun Link.detectForShiftingMultiplication() = object : Detector() {
        override val validEvent = ValidIndirectShiftingMultiplicationOutOfDivision
        override val invalidEvent = InvalidIndirectShiftingMultiplicationOfZeroOutOfDivision
    }.detect(this)

    private abstract class Detector {
        abstract val validEvent: IndirectShiftingOutOfDivisionEvent
        abstract val invalidEvent: IndirectShiftingOutOfDivisionEvent


        fun detect(link: Link) = with(link) {
            if(isInvolvingAtLeastOneZero()) {
                invalidEvent
            } else {
                validEvent
            }
        }

        private fun Link.isInvolvingAtLeastOneZero() =
                source.any(Term::isZero)
    }
}