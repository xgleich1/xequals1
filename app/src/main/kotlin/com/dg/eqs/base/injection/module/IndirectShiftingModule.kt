package com.dg.eqs.base.injection.module

import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingDetector
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingExecutor
import com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting.InvalidShiftingDueToBracketingDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.invalidshifting.InvalidShiftingDueToOrderOfOperationsDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionCalculator
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionDecider
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionDetector
import com.dg.eqs.core.execution.intention.link.indirectshifting.shiftingoutofdivision.IndirectShiftingOutOfDivisionExecutor
import dagger.Module
import dagger.Provides


@Module
open class IndirectShiftingModule {
    @Provides
    open fun provideIndirectShiftingExecutor(
            indirectShiftingDetector: IndirectShiftingDetector,
            indirectShiftingOutOfDivisionExecutor: IndirectShiftingOutOfDivisionExecutor): IndirectShiftingExecutor {

        return IndirectShiftingExecutor(
                indirectShiftingDetector,
                indirectShiftingOutOfDivisionExecutor)
    }

    @Provides
    open fun provideIndirectShiftingDetector(
            indirectShiftingOutOfDivisionDecider: IndirectShiftingOutOfDivisionDecider,
            invalidShiftingDueToOrderOfOperationsDecider: InvalidShiftingDueToOrderOfOperationsDecider,
            invalidShiftingDueToBracketingDecider: InvalidShiftingDueToBracketingDecider): IndirectShiftingDetector {

        return IndirectShiftingDetector(
                indirectShiftingOutOfDivisionDecider,
                invalidShiftingDueToOrderOfOperationsDecider,
                invalidShiftingDueToBracketingDecider)
    }

    @Provides
    open fun provideIndirectShiftingOutOfDivisionDecider(): IndirectShiftingOutOfDivisionDecider {
        return IndirectShiftingOutOfDivisionDecider()
    }

    @Provides
    open fun provideInvalidShiftingDueToOrderOfOperationsDecider(): InvalidShiftingDueToOrderOfOperationsDecider {
        return InvalidShiftingDueToOrderOfOperationsDecider()
    }

    @Provides
    open fun provideInvalidShiftingDueToBracketingDecider(): InvalidShiftingDueToBracketingDecider {
        return InvalidShiftingDueToBracketingDecider()
    }

    @Provides
    open fun provideIndirectShiftingOutOfDivisionExecutor(
            indirectShiftingOutOfDivisionDetector: IndirectShiftingOutOfDivisionDetector,
            indirectShiftingOutOfDivisionCalculator: IndirectShiftingOutOfDivisionCalculator): IndirectShiftingOutOfDivisionExecutor {

        return IndirectShiftingOutOfDivisionExecutor(
                indirectShiftingOutOfDivisionDetector,
                indirectShiftingOutOfDivisionCalculator)
    }

    @Provides
    open fun provideIndirectShiftingOutOfDivisionDetector(): IndirectShiftingOutOfDivisionDetector {
        return IndirectShiftingOutOfDivisionDetector()
    }

    @Provides
    open fun provideIndirectShiftingOutOfDivisionCalculator(): IndirectShiftingOutOfDivisionCalculator {
        return IndirectShiftingOutOfDivisionCalculator()
    }
}