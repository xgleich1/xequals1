package com.dg.eqs.core.visualization.composition.vertical.operation.division

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.visualization.composition.horizontal.envelope.ContainerDraft


class PositiveDivisionDraft(origin: Division, numerator: TermDraft, denominator: TermDraft)
    : ContainerDraft<Division>(RawDivisionDraft(origin, numerator, denominator)) {

    init {
        numerator.choosableParent = this
        denominator.choosableParent = this
    }
}