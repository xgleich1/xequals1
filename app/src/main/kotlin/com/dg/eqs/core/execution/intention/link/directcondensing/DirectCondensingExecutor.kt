package com.dg.eqs.core.execution.intention.link.directcondensing

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingEvent.*
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductExecutor


class DirectCondensingExecutor(
        private val directCondensingDetector: DirectCondensingDetector,
        private val singleSelectionCondensingInDashOperationExecutor: SingleSelectionCondensingInDashOperationExecutor,
        private val singleSelectionCondensingInDivisionExecutor: SingleSelectionCondensingInDivisionExecutor,
        private val singleSelectionCondensingInProductExecutor: SingleSelectionCondensingInProductExecutor,
        private val multiSelectionCondensingInDashOperationExecutor: MultiSelectionCondensingInDashOperationExecutor,
        private val multiSelectionCondensingInProductExecutor: MultiSelectionCondensingInProductExecutor) {

    fun execute(link: Link) = when(directCondensingDetector.detect(link)) {
        SingleSelectionCondensingInDashOperation -> singleSelectionCondensingInDashOperationExecutor.execute(link)
        SingleSelectionCondensingInDivision -> singleSelectionCondensingInDivisionExecutor.execute(link)
        SingleSelectionCondensingInProduct -> singleSelectionCondensingInProductExecutor.execute(link)
        MultiSelectionCondensingInDashOperation -> multiSelectionCondensingInDashOperationExecutor.execute(link)
        MultiSelectionCondensingInProduct -> multiSelectionCondensingInProductExecutor.execute(link)
    }
}