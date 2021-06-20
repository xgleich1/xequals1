package com.dg.eqs.base.injection.module

import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingCalculator
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensingindashoperation.MultiSelectionCondensingInDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.multiselectioncondensinginproduct.MultiSelectionCondensingInProductExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindashoperation.SingleSelectionCondensingInDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensingindivision.SingleSelectionCondensingInDivisionExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductDetector
import com.dg.eqs.core.execution.intention.link.directcondensing.singleselectioncondensinginproduct.SingleSelectionCondensingInProductExecutor
import dagger.Module
import dagger.Provides


@Module
open class DirectCondensingModule {
    @Provides
    open fun provideDirectCondensingExecutor(
            directCondensingDetector: DirectCondensingDetector,
            singleSelectionCondensingInDashOperationExecutor: SingleSelectionCondensingInDashOperationExecutor,
            singleSelectionCondensingInDivisionExecutor: SingleSelectionCondensingInDivisionExecutor,
            singleSelectionCondensingInProductExecutor: SingleSelectionCondensingInProductExecutor,
            multiSelectionCondensingInDashOperationExecutor: MultiSelectionCondensingInDashOperationExecutor,
            multiSelectionCondensingInProductExecutor: MultiSelectionCondensingInProductExecutor): DirectCondensingExecutor {

        return DirectCondensingExecutor(
                directCondensingDetector,
                singleSelectionCondensingInDashOperationExecutor,
                singleSelectionCondensingInDivisionExecutor,
                singleSelectionCondensingInProductExecutor,
                multiSelectionCondensingInDashOperationExecutor,
                multiSelectionCondensingInProductExecutor)
    }

    @Provides
    open fun provideDirectCondensingDetector(): DirectCondensingDetector {
        return DirectCondensingDetector()
    }

    @Provides
    open fun provideDirectCondensingCalculator(): DirectCondensingCalculator {
        return DirectCondensingCalculator()
    }

    @Provides
    open fun provideSingleSelectionCondensingInDashOperationExecutor(
            singleSelectionCondensingInDashOperationDetector: SingleSelectionCondensingInDashOperationDetector,
            directCondensingCalculator: DirectCondensingCalculator): SingleSelectionCondensingInDashOperationExecutor {

        return SingleSelectionCondensingInDashOperationExecutor(
                singleSelectionCondensingInDashOperationDetector,
                directCondensingCalculator)
    }

    @Provides
    open fun provideSingleSelectionCondensingInDashOperationDetector(): SingleSelectionCondensingInDashOperationDetector {
        return SingleSelectionCondensingInDashOperationDetector()
    }

    @Provides
    open fun provideSingleSelectionCondensingInDivisionExecutor(
            singleSelectionCondensingInDivisionDetector: SingleSelectionCondensingInDivisionDetector,
            directCondensingCalculator: DirectCondensingCalculator): SingleSelectionCondensingInDivisionExecutor {

        return SingleSelectionCondensingInDivisionExecutor(
                singleSelectionCondensingInDivisionDetector,
                directCondensingCalculator)
    }

    @Provides
    open fun provideSingleSelectionCondensingInDivisionDetector(): SingleSelectionCondensingInDivisionDetector {
        return SingleSelectionCondensingInDivisionDetector()
    }

    @Provides
    open fun provideSingleSelectionCondensingInProductExecutor(
            singleSelectionCondensingInProductDetector: SingleSelectionCondensingInProductDetector,
            directCondensingCalculator: DirectCondensingCalculator): SingleSelectionCondensingInProductExecutor {

        return SingleSelectionCondensingInProductExecutor(
                singleSelectionCondensingInProductDetector,
                directCondensingCalculator)
    }

    @Provides
    open fun provideSingleSelectionCondensingInProductDetector(): SingleSelectionCondensingInProductDetector {
        return SingleSelectionCondensingInProductDetector()
    }

    @Provides
    open fun provideMultiSelectionCondensingInDashOperationExecutor(
            multiSelectionCondensingInDashOperationDetector: MultiSelectionCondensingInDashOperationDetector,
            directCondensingCalculator: DirectCondensingCalculator): MultiSelectionCondensingInDashOperationExecutor {

        return MultiSelectionCondensingInDashOperationExecutor(
                multiSelectionCondensingInDashOperationDetector,
                directCondensingCalculator)
    }

    @Provides
    open fun provideMultiSelectionCondensingInDashOperationDetector(): MultiSelectionCondensingInDashOperationDetector {
        return MultiSelectionCondensingInDashOperationDetector()
    }

    @Provides
    open fun provideMultiSelectionCondensingInProductExecutor(
            multiSelectionCondensingInProductDetector: MultiSelectionCondensingInProductDetector,
            directCondensingCalculator: DirectCondensingCalculator): MultiSelectionCondensingInProductExecutor {

        return MultiSelectionCondensingInProductExecutor(
                multiSelectionCondensingInProductDetector,
                directCondensingCalculator)
    }

    @Provides
    open fun provideMultiSelectionCondensingInProductDetector(): MultiSelectionCondensingInProductDetector {
        return MultiSelectionCondensingInProductDetector()
    }
}