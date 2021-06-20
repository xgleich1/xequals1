package com.dg.eqs.core.visualization.composition.horizontal.operation.dashoperation

import com.dg.eqs.base.abbreviation.TermDraft
import com.dg.eqs.base.abbreviation.TermDrafts
import com.dg.eqs.base.abbreviation.draftsOf
import com.dg.eqs.core.definition.term.operation.dashoperation.DashOperation
import com.dg.eqs.core.visualization.composition.horizontal.envelope.ContainerDraft


class PositiveDashOperationDraft(origin: DashOperation, operands: TermDrafts)
    : ContainerDraft<DashOperation>(RawDashOperationDraft(origin, operands)) {

    init {
        operands.forEach {
            it.choosableParent = this
        }
    }

    constructor(origin: DashOperation, vararg operands: TermDraft) : this(origin, draftsOf(*operands))
}