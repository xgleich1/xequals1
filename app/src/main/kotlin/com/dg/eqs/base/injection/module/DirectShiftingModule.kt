package com.dg.eqs.base.injection.module

import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingCalculator
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingDetector
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideDetector
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingofentireside.ShiftingOfEntireSideExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationDetector
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdashoperation.ShiftingOutOfDashOperationExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionDetector
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofdivision.ShiftingOutOfDivisionExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductDetector
import com.dg.eqs.core.execution.intention.link.directshifting.shiftingoutofproduct.ShiftingOutOfProductExecutor
import dagger.Module
import dagger.Provides


@Module
open class DirectShiftingModule {
    @Provides
    open fun provideDirectShiftingExecutor(
            directShiftingDetector: DirectShiftingDetector,
            shiftingOfEntireSideExecutor: ShiftingOfEntireSideExecutor,
            shiftingOutOfDashOperationExecutor: ShiftingOutOfDashOperationExecutor,
            shiftingOutOfDivisionExecutor: ShiftingOutOfDivisionExecutor,
            shiftingOutOfProductExecutor: ShiftingOutOfProductExecutor): DirectShiftingExecutor {

        return DirectShiftingExecutor(
                directShiftingDetector,
                shiftingOfEntireSideExecutor,
                shiftingOutOfDashOperationExecutor,
                shiftingOutOfDivisionExecutor,
                shiftingOutOfProductExecutor)
    }

    @Provides
    open fun provideDirectShiftingDetector(): DirectShiftingDetector {
        return DirectShiftingDetector()
    }

    @Provides
    open fun provideDirectShiftingCalculator(): DirectShiftingCalculator {
        return DirectShiftingCalculator()
    }

    @Provides
    open fun provideShiftingOfEntireSideExecutor(
            shiftingOfEntireSideDetector: ShiftingOfEntireSideDetector,
            directShiftingCalculator: DirectShiftingCalculator): ShiftingOfEntireSideExecutor {

        return ShiftingOfEntireSideExecutor(
                shiftingOfEntireSideDetector,
                directShiftingCalculator)
    }

    @Provides
    open fun provideShiftingOfEntireSideDetector(): ShiftingOfEntireSideDetector {
        return ShiftingOfEntireSideDetector()
    }

    @Provides
    open fun provideShiftingOutOfDashOperationExecutor(
            shiftingOutOfDashOperationDetector: ShiftingOutOfDashOperationDetector,
            directShiftingCalculator: DirectShiftingCalculator): ShiftingOutOfDashOperationExecutor {

        return ShiftingOutOfDashOperationExecutor(
                shiftingOutOfDashOperationDetector,
                directShiftingCalculator)
    }

    @Provides
    open fun provideShiftingOutOfDashOperationDetector(): ShiftingOutOfDashOperationDetector {
        return ShiftingOutOfDashOperationDetector()
    }

    @Provides
    open fun provideShiftingOutOfDivisionExecutor(
            shiftingOutOfDivisionDetector: ShiftingOutOfDivisionDetector,
            directShiftingCalculator: DirectShiftingCalculator): ShiftingOutOfDivisionExecutor {

        return ShiftingOutOfDivisionExecutor(
                shiftingOutOfDivisionDetector,
                directShiftingCalculator)
    }

    @Provides
    open fun provideShiftingOutOfDivisionDetector(): ShiftingOutOfDivisionDetector {
        return ShiftingOutOfDivisionDetector()
    }

    @Provides
    open fun provideShiftingOutOfProductExecutor(
            shiftingOutOfProductDetector: ShiftingOutOfProductDetector,
            directShiftingCalculator: DirectShiftingCalculator): ShiftingOutOfProductExecutor {

        return ShiftingOutOfProductExecutor(
                shiftingOutOfProductDetector,
                directShiftingCalculator)
    }

    @Provides
    open fun provideShiftingOutOfProductDetector(): ShiftingOutOfProductDetector {
        return ShiftingOutOfProductDetector()
    }
}