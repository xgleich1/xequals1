package com.dg.eqs.base.injection.module

import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.CondensingReduceExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceCalculator
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.SingleSelectionReduceDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator.SingleSelectionReduceWithSourceInDenominatorCalculator
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator.SingleSelectionReduceWithSourceInDenominatorDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.sourceindenominator.SingleSelectionReduceWithSourceInDenominatorExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator.SingleSelectionReduceWithTargetInDenominatorCalculator
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator.SingleSelectionReduceWithTargetInDenominatorDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.condensingreduce.singleselection.targetindenominator.SingleSelectionReduceWithTargetInDenominatorExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToBracketingDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToOrderOfOperationsDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.invalidcondensing.InvalidCondensingDueToReduceWithDashOperationDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationDecider
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.NumeratorMultiplicationExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationCalculator
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.SingleSelectionNumeratorMultiplicationDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft.SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft.SingleSelectionNumeratorMultiplicationWithSourceLeftDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.sourceleft.SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft.SingleSelectionNumeratorMultiplicationWithTargetLeftCalculator
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft.SingleSelectionNumeratorMultiplicationWithTargetLeftDetector
import com.dg.eqs.core.execution.intention.link.indirectcondensing.numeratormultiplication.singleselection.targetleft.SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor
import dagger.Module
import dagger.Provides


@Module
open class IndirectCondensingModule {
    @Provides
    open fun provideIndirectCondensingExecutor(
            indirectCondensingDetector: IndirectCondensingDetector,
            condensingReduceExecutor: CondensingReduceExecutor,
            numeratorMultiplicationExecutor: NumeratorMultiplicationExecutor): IndirectCondensingExecutor {

        return IndirectCondensingExecutor(
                indirectCondensingDetector,
                condensingReduceExecutor,
                numeratorMultiplicationExecutor)
    }

    @Provides
    open fun provideIndirectCondensingDetector(
            condensingReduceDecider: CondensingReduceDecider,
            numeratorMultiplicationDecider: NumeratorMultiplicationDecider,
            invalidCondensingDueToReduceWithDashOperationDecider: InvalidCondensingDueToReduceWithDashOperationDecider,
            invalidCondensingDueToOrderOfOperationsDecider: InvalidCondensingDueToOrderOfOperationsDecider,
            invalidCondensingDueToBracketingDecider: InvalidCondensingDueToBracketingDecider): IndirectCondensingDetector {

        return IndirectCondensingDetector(
                condensingReduceDecider,
                numeratorMultiplicationDecider,
                invalidCondensingDueToReduceWithDashOperationDecider,
                invalidCondensingDueToOrderOfOperationsDecider,
                invalidCondensingDueToBracketingDecider)
    }

    @Provides
    open fun provideCondensingReduceDecider(): CondensingReduceDecider {
        return CondensingReduceDecider()
    }

    @Provides
    open fun provideNumeratorMultiplicationDecider(): NumeratorMultiplicationDecider {
        return NumeratorMultiplicationDecider()
    }

    @Provides
    open fun provideInvalidCondensingDueToReduceWithDashOperationDecider(): InvalidCondensingDueToReduceWithDashOperationDecider {
        return InvalidCondensingDueToReduceWithDashOperationDecider()
    }

    @Provides
    open fun provideInvalidCondensingDueToOrderOfOperationsDecider(): InvalidCondensingDueToOrderOfOperationsDecider {
        return InvalidCondensingDueToOrderOfOperationsDecider()
    }

    @Provides
    open fun provideInvalidCondensingDueToBracketingDecider(): InvalidCondensingDueToBracketingDecider {
        return InvalidCondensingDueToBracketingDecider()
    }

    @Provides
    open fun provideCondensingReduceExecutor(
            condensingReduceDetector: CondensingReduceDetector,
            singleSelectionReduceWithSourceInDenominatorExecutor: SingleSelectionReduceWithSourceInDenominatorExecutor,
            singleSelectionReduceWithTargetInDenominatorExecutor: SingleSelectionReduceWithTargetInDenominatorExecutor): CondensingReduceExecutor {

        return CondensingReduceExecutor(
                condensingReduceDetector,
                singleSelectionReduceWithSourceInDenominatorExecutor,
                singleSelectionReduceWithTargetInDenominatorExecutor)
    }

    @Provides
    open fun provideCondensingReduceDetector(): CondensingReduceDetector {
        return CondensingReduceDetector()
    }

    @Provides
    open fun provideSingleSelectionReduceWithSourceInDenominatorExecutor(
            singleSelectionReduceWithSourceInDenominatorDetector: SingleSelectionReduceWithSourceInDenominatorDetector,
            singleSelectionReduceWithSourceInDenominatorCalculator: SingleSelectionReduceWithSourceInDenominatorCalculator): SingleSelectionReduceWithSourceInDenominatorExecutor {

        return SingleSelectionReduceWithSourceInDenominatorExecutor(
                singleSelectionReduceWithSourceInDenominatorDetector,
                singleSelectionReduceWithSourceInDenominatorCalculator)
    }

    @Provides
    open fun provideSingleSelectionReduceWithSourceInDenominatorDetector(
            singleSelectionReduceDetector: SingleSelectionReduceDetector): SingleSelectionReduceWithSourceInDenominatorDetector {

        return SingleSelectionReduceWithSourceInDenominatorDetector(singleSelectionReduceDetector)
    }

    @Provides
    open fun provideSingleSelectionReduceWithSourceInDenominatorCalculator(
            singleSelectionReduceCalculator: SingleSelectionReduceCalculator): SingleSelectionReduceWithSourceInDenominatorCalculator {

        return SingleSelectionReduceWithSourceInDenominatorCalculator(singleSelectionReduceCalculator)
    }

    @Provides
    open fun provideSingleSelectionReduceWithTargetInDenominatorExecutor(
            singleSelectionReduceWithTargetInDenominatorDetector: SingleSelectionReduceWithTargetInDenominatorDetector,
            singleSelectionReduceWithTargetInDenominatorCalculator: SingleSelectionReduceWithTargetInDenominatorCalculator): SingleSelectionReduceWithTargetInDenominatorExecutor {

        return SingleSelectionReduceWithTargetInDenominatorExecutor(
                singleSelectionReduceWithTargetInDenominatorDetector,
                singleSelectionReduceWithTargetInDenominatorCalculator)
    }

    @Provides
    open fun provideSingleSelectionReduceWithTargetInDenominatorDetector(
            singleSelectionReduceDetector: SingleSelectionReduceDetector): SingleSelectionReduceWithTargetInDenominatorDetector {

        return SingleSelectionReduceWithTargetInDenominatorDetector(singleSelectionReduceDetector)
    }

    @Provides
    open fun provideSingleSelectionReduceWithTargetInDenominatorCalculator(
            singleSelectionReduceCalculator: SingleSelectionReduceCalculator): SingleSelectionReduceWithTargetInDenominatorCalculator {

        return SingleSelectionReduceWithTargetInDenominatorCalculator(singleSelectionReduceCalculator)
    }

    @Provides
    open fun provideSingleSelectionReduceDetector(
            singleSelectionCondensingInDivisionDetector: SingleSelectionCondensingInDivisionDetector): SingleSelectionReduceDetector {

        return SingleSelectionReduceDetector(singleSelectionCondensingInDivisionDetector)
    }

    @Provides
    open fun provideSingleSelectionReduceCalculator(): SingleSelectionReduceCalculator {
        return SingleSelectionReduceCalculator()
    }

    @Provides
    open fun provideNumeratorMultiplicationExecutor(
            numeratorMultiplicationDetector: NumeratorMultiplicationDetector,
            singleSelectionNumeratorMultiplicationWithSourceLeftExecutor: SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor,
            singleSelectionNumeratorMultiplicationWithTargetLeftExecutor: SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor): NumeratorMultiplicationExecutor {

        return NumeratorMultiplicationExecutor(
                numeratorMultiplicationDetector,
                singleSelectionNumeratorMultiplicationWithSourceLeftExecutor,
                singleSelectionNumeratorMultiplicationWithTargetLeftExecutor)
    }

    @Provides
    open fun provideNumeratorMultiplicationDetector(): NumeratorMultiplicationDetector {
        return NumeratorMultiplicationDetector()
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationWithSourceLeftExecutor(
            singleSelectionNumeratorMultiplicationWithSourceLeftDetector: SingleSelectionNumeratorMultiplicationWithSourceLeftDetector,
            singleSelectionNumeratorMultiplicationWithSourceLeftCalculator: SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator): SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor {

        return SingleSelectionNumeratorMultiplicationWithSourceLeftExecutor(
                singleSelectionNumeratorMultiplicationWithSourceLeftDetector,
                singleSelectionNumeratorMultiplicationWithSourceLeftCalculator)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationWithSourceLeftDetector(
            singleSelectionNumeratorMultiplicationDetector: SingleSelectionNumeratorMultiplicationDetector): SingleSelectionNumeratorMultiplicationWithSourceLeftDetector {

        return SingleSelectionNumeratorMultiplicationWithSourceLeftDetector(singleSelectionNumeratorMultiplicationDetector)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationWithSourceLeftCalculator(
            singleSelectionNumeratorMultiplicationCalculator: SingleSelectionNumeratorMultiplicationCalculator): SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator {

        return SingleSelectionNumeratorMultiplicationWithSourceLeftCalculator(singleSelectionNumeratorMultiplicationCalculator)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationWithTargetLeftExecutor(
            singleSelectionNumeratorMultiplicationWithTargetLeftDetector: SingleSelectionNumeratorMultiplicationWithTargetLeftDetector,
            singleSelectionNumeratorMultiplicationWithTargetLeftCalculator: SingleSelectionNumeratorMultiplicationWithTargetLeftCalculator): SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor {

        return SingleSelectionNumeratorMultiplicationWithTargetLeftExecutor(
                singleSelectionNumeratorMultiplicationWithTargetLeftDetector,
                singleSelectionNumeratorMultiplicationWithTargetLeftCalculator)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationWithTargetLeftDetector(
            singleSelectionNumeratorMultiplicationDetector: SingleSelectionNumeratorMultiplicationDetector): SingleSelectionNumeratorMultiplicationWithTargetLeftDetector {

        return SingleSelectionNumeratorMultiplicationWithTargetLeftDetector(singleSelectionNumeratorMultiplicationDetector)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationWithTargetLeftCalculator(
            singleSelectionNumeratorMultiplicationCalculator: SingleSelectionNumeratorMultiplicationCalculator): SingleSelectionNumeratorMultiplicationWithTargetLeftCalculator {

        return SingleSelectionNumeratorMultiplicationWithTargetLeftCalculator(singleSelectionNumeratorMultiplicationCalculator)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationDetector(
            singleSelectionCondensingInProductDetector: SingleSelectionCondensingInProductDetector): SingleSelectionNumeratorMultiplicationDetector {

        return SingleSelectionNumeratorMultiplicationDetector(singleSelectionCondensingInProductDetector)
    }

    @Provides
    open fun provideSingleSelectionNumeratorMultiplicationCalculator(): SingleSelectionNumeratorMultiplicationCalculator {
        return SingleSelectionNumeratorMultiplicationCalculator()
    }
}