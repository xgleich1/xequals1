package com.dg.eqs.base.concurrency


val ExecutionScope.coroutineScope get() = executionConfig.scope

val ExecutionScope.coroutineContext get() = executionConfig.context