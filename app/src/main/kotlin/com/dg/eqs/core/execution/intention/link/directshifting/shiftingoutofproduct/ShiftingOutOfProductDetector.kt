package com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct

import com.dg.eqs.core.definition.term.Term
import com.dg.eqs.core.definition.term.isZero
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.ValidShiftingDivisionOutOfProduct
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductEvent.InvalidShiftingDivisionOfZeroOutOfProduct


class ShiftingOutOfProductDetector {
    fun detect(link: Link) = with(link) {
        if(isInvolvingAtLeastOneZero()) {
            InvalidShiftingDivisionOfZeroOutOfProduct
        } else {
            ValidShiftingDivisionOutOfProduct
        }
    }

    private fun Link.isInvolvingAtLeastOneZero() =
            source.any(Term::isZero)
}