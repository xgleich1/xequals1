package com.dg.eqs.core.execution.intention.link.indirectcondensing

import com.dg.eqs.core.execution.intention.Intention.Link
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingEvent.*
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToBracketingDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToOrderOfOperationsDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToReduceWithDashOperationDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationDecider


class IndirectCondensingDetector(
        private val condensingReduceDecider: CondensingReduceDecider,
        private val numeratorMultiplicationDecider: NumeratorMultiplicationDecider,
        private val invalidCondensingDueToReduceWithDashOperationDecider: InvalidCondensingDueToReduceWithDashOperationDecider,
        private val invalidCondensingDueToOrderOfOperationsDecider: InvalidCondensingDueToOrderOfOperationsDecider,
        private val invalidCondensingDueToBracketingDecider: InvalidCondensingDueToBracketingDecider) {

    fun detect(link: Link) = when {
        condensingReduceDecider.decide(link) -> CondensingReduce
        numeratorMultiplicationDecider.decide(link) -> NumeratorMultiplication
        invalidCondensingDueToReduceWithDashOperationDecider.decide(link) -> InvalidIndirectCondensingDueToReduceWithDashOperation
        invalidCondensingDueToOrderOfOperationsDecider.decide(link) -> InvalidIndirectCondensingDueToOrderOfOperations
        invalidCondensingDueToBracketingDecider.decide(link) -> InvalidIndirectCondensingDueToBracketing

        else -> InvalidIndirectCondensing
    }
}