package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision

import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionEvent.*


class ShiftingOutOfDivisionDetector {
    private val Link.division
        get() = sourceParent as Division


    fun detect(link: Link) = with(link) {
        if(isShiftingDivision()) {
            detectForShiftingDivision()
        } else {
            detectForShiftingMultiplication()
        }
    }

    private fun Link.isShiftingDivision() =
            firstSource === division.numerator

    private fun Link.detectForShiftingDivision() = object : Detector() {
        override val validEvent = ValidShiftingDivisionOutOfDivision
        override val invalidEvent = InvalidShiftingDivisionOfZeroOutOfDivision
    }.detect(this)

    private fun Link.detectForShiftingMultiplication() = object : Detector() {
        override val validEvent = ValidShiftingMultiplicationOutOfDivision
        override val invalidEvent = InvalidShiftingMultiplicationOfZeroOutOfDivision
    }.detect(this)

    private abstract class Detector {
        abstract val validEvent: ShiftingOutOfDivisionEvent
        abstract val invalidEvent: ShiftingOutOfDivisionEvent


        fun detect(link: Link) = with(link) {
            if(firstSource.isZero) {
                invalidEvent
            } else {
                validEvent
            }
        }
    }
}