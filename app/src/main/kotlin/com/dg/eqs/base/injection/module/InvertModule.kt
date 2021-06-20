package com.dg.eqs.base.injection.module

import com.dg.eqs.core.execution.intention.click.invert.InvertCalculator
import com.dg.eqs.core.execution.intention.click.invert.InvertExecutor
import dagger.Module
import dagger.Provides


@Module
open class InvertModule {
    @Provides
    open fun provideInvertExecutor(
            invertCalculator: InvertCalculator): InvertExecutor {

        return InvertExecutor(invertCalculator)
    }

    @Provides
    open fun provideInvertCalculator(): InvertCalculator {
        return InvertCalculator()
    }
}