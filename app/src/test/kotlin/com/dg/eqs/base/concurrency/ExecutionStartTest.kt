package com.dg.eqs.base.concurrency

import com.dg.eqs.base.concurrency.ExecutionStart.IMMEDIATE
import com.dg.eqs.base.concurrency.ExecutionStart.LAZY
import kotlinx.coroutines.CoroutineStart
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ExecutionStartTest {
    @Test
    fun `The immediate execution start relates to the default coroutine start`() {
        assertThat(IMMEDIATE.coroutineStart).isSameAs(CoroutineStart.DEFAULT)
    }

    @Test
    fun `The lazy execution start relates to the lazy coroutine start`() {
        assertThat(LAZY.coroutineStart).isSameAs(CoroutineStart.LAZY)
    }
}