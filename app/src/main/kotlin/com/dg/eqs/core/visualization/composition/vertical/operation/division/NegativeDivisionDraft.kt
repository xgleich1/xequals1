package com.dg.eqs.core.visualization.composition.vertical.operation.division

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.core.definition.term.operation.dotoperation.division.Division
import com.dg.eqs.core.visualization.composition.horizontal.envelope.ContainerDraft
import com.dg.eqs.core.visualization.composition.horizontal.envelope.NegationDraft


class NegativeDivisionDraft(origin: Division, numerator: TermDraft, denominator: TermDraft)
    : ContainerDraft<Division>(NegationDraft(RawDivisionDraft(origin, numerator, denominator))) {

    init {
        numerator.choosableParent = this
        denominator.choosableParent = this
    }
}