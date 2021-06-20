package com.dg.eqs.core.interaction.linking.choosing.widening

import com.dg.eqs.base.concurrency.BackgroundExecutor
import com.dg.eqs.base.concurrency.ExecutionConfig.SupervisorJobExecutionConfig
import com.dg.eqs.base.concurrency.ExecutionContext.MAIN
import com.dg.eqs.base.concurrency.ExecutionScope
import kotlinx.coroutines.delay


class WideningDelayer(
        private val backgroundExecutor: BackgroundExecutor,
        private val wideningTiming: WideningTiming) : ExecutionScope {

    override val executionConfig = SupervisorJobExecutionConfig()


    fun delay(widening: () -> Unit) {
        cancel()

        backgroundExecutor.launch(MAIN, this) {
            delay(wideningTiming.delayInMs)

            widening()
        }
    }

    fun cancel() = backgroundExecutor.cancelExecutions(this)
}