package com.dg.eqs.base.concurrency

import kotlinx.coroutines.CoroutineStart


enum class ExecutionStart(val coroutineStart: CoroutineStart) {
    IMMEDIATE(CoroutineStart.DEFAULT),
    LAZY(CoroutineStart.LAZY)
}