package com.dg.eqs.base.concurrency

import com.dg.eqs.base.concurrency.ExecutionContext.*
import kotlinx.coroutines.Dispatchers
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ExecutionContextTest {
    @Test
    fun `The main execution context relates to the main coroutine dispatcher`() {
        assertThat(MAIN.coroutineDispatcher).isSameAs(Dispatchers.Main)
    }

    @Test
    fun `The cpu execution context relates to the default coroutine dispatcher`() {
        assertThat(CPU.coroutineDispatcher).isSameAs(Dispatchers.Default)
    }

    @Test
    fun `The io execution context relates to the io coroutine dispatcher`() {
        assertThat(IO.coroutineDispatcher).isSameAs(Dispatchers.IO)
    }
}