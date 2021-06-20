package com.dg.eqs.base.concurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


sealed class ExecutionConfig(val context: CoroutineContext) {
    val scope = object : CoroutineScope {
        override val coroutineContext = context
    }

    class JobExecutionConfig : ExecutionConfig(Job())

    class SupervisorJobExecutionConfig : ExecutionConfig(SupervisorJob())
}