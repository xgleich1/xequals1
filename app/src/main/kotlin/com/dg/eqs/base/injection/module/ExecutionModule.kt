package com.dg.eqs.base.injection.module

import com.dg.eqs.core.execution.intention.IntentionExecutor
import com.dg.eqs.core.execution.intention.click.ClickIntentionExecutor
import com.dg.eqs.core.execution.intention.click.invert.InvertExecutor
import com.dg.eqs.core.execution.intention.link.LinkIntentionDetector
import com.dg.eqs.core.execution.intention.link.LinkIntentionExecutor
import com.dg.eqs.core.execution.intention.link.directcondensing.DirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.directshifting.DirectShiftingExecutor
import com.dg.eqs.core.execution.intention.link.indirectcondensing.IndirectCondensingExecutor
import com.dg.eqs.core.execution.intention.link.indirectshifting.IndirectShiftingExecutor
import dagger.Module
import dagger.Provides


@Module
open class ExecutionModule {
    @Provides
    open fun provideIntentionExecutor(
            linkIntentionExecutor: LinkIntentionExecutor,
            clickIntentionExecutor: ClickIntentionExecutor): IntentionExecutor {

        return IntentionExecutor(
                linkIntentionExecutor,
                clickIntentionExecutor)
    }

    @Provides
    open fun provideLinkIntentionExecutor(
            linkIntentionDetector: LinkIntentionDetector,
            directShiftingExecutor: DirectShiftingExecutor,
            indirectShiftingExecutor: IndirectShiftingExecutor,
            directCondensingExecutor: DirectCondensingExecutor,
            indirectCondensingExecutor: IndirectCondensingExecutor): LinkIntentionExecutor {

        return LinkIntentionExecutor(
                linkIntentionDetector,
                directShiftingExecutor,
                indirectShiftingExecutor,
                directCondensingExecutor,
                indirectCondensingExecutor)
    }

    @Provides
    open fun provideLinkIntentionDetector(): LinkIntentionDetector {
        return LinkIntentionDetector()
    }

    @Provides
    open fun provideClickIntentionExecutor(
            invertExecutor: InvertExecutor): ClickIntentionExecutor {

        return ClickIntentionExecutor(invertExecutor)
    }
}