package com.dg.eqs.base.concurrency

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


enum class ExecutionContext(val coroutineDispatcher: CoroutineDispatcher) {
    MAIN(Dispatchers.Main),
    CPU(Dispatchers.Default),
    IO(Dispatchers.IO)
}