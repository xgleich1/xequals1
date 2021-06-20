package com.dg.eqs.base.concurrency

import com.dg.eqs.base.concurrency.ExecutionStart.IMMEDIATE
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job


interface BackgroundExecutor {
    fun launch(context: ExecutionContext, start: ExecutionStart = IMMEDIATE, action: suspend () -> Unit): Job

    fun <T> async(context: ExecutionContext, start: ExecutionStart = IMMEDIATE, action: suspend () -> T): Deferred<T>

    fun launch(context: ExecutionContext, scope: ExecutionScope, start: ExecutionStart = IMMEDIATE, action: suspend () -> Unit): Job

    fun <T> async(context: ExecutionContext, scope: ExecutionScope, start: ExecutionStart = IMMEDIATE, action: suspend () -> T): Deferred<T>

    fun cancelExecutions(scope: ExecutionScope)
}