package com.dg.eqs.base.concurrency

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch


class BackgroundExecutorImpl : BackgroundExecutor {
    override fun launch(context: ExecutionContext, start: ExecutionStart, action: suspend () -> Unit) =
            GlobalScope.launch(context.coroutineDispatcher, start.coroutineStart) { action() }

    override fun <T> async(context: ExecutionContext, start: ExecutionStart, action: suspend () -> T) =
            GlobalScope.async(context.coroutineDispatcher, start.coroutineStart) { action() }

    override fun launch(context: ExecutionContext, scope: ExecutionScope, start: ExecutionStart, action: suspend () -> Unit) =
            scope.coroutineScope.launch(context.coroutineDispatcher, start.coroutineStart) { action() }

    override fun <T> async(context: ExecutionContext, scope: ExecutionScope, start: ExecutionStart, action: suspend () -> T) =
            scope.coroutineScope.async(context.coroutineDispatcher, start.coroutineStart) { action() }

    override fun cancelExecutions(scope: ExecutionScope) =
            scope.coroutineContext.cancelChildren()
}