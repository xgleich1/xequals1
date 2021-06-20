package com.dg.eqs.core.execution.intention.link.indirectcondensing

import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDueToBracketingInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingDueToOrderOfOperationsInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingInfo
import com.dg.eqs.core.information.invalid.condensing.InvalidCondensingReduceWithDashOperationInfo
import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.Step.InvalidStep
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationExecutor


class IndirectCondensingExecutor(
        private val indirectCondensingDetector: IndirectCondensingDetector,
        private val condensingReduceExecutor: CondensingReduceExecutor,
        private val numeratorMultiplicationExecutor: NumeratorMultiplicationExecutor) {

    fun execute(link: Link) = when(indirectCondensingDetector.detect(link)) {
        CondensingReduce -> condensingReduceExecutor.execute(link)
        NumeratorMultiplication -> numeratorMultiplicationExecutor.execute(link)
        InvalidIndirectCondensingDueToReduceWithDashOperation -> InvalidStep(InvalidCondensingReduceWithDashOperationInfo, link.origin)
        InvalidIndirectCondensingDueToOrderOfOperations -> InvalidStep(InvalidCondensingDueToOrderOfOperationsInfo, link.origin)
        InvalidIndirectCondensingDueToBracketing -> InvalidStep(InvalidCondensingDueToBracketingInfo, link.origin)
        InvalidIndirectCondensing -> InvalidStep(InvalidCondensingInfo, link.origin)
    }
}