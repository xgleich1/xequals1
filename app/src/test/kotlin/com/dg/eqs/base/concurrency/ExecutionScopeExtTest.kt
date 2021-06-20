package com.dg.eqs.base.concurrency

import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import kotlinx.coroutines.CoroutineScope
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import kotlin.coroutines.CoroutineContext


class ExecutionScopeExtTest {
    @Test
    fun `Should provide a shortcut to the execution config's coroutine scope`() {
        // GIVEN
        val coroutineScope: CoroutineScope = mock()

        val executionConfig: ExecutionConfig = mock {
            on { scope } doReturn coroutineScope
        }

        val executionScope = createExecutionScope(executionConfig)

        // THEN
        assertThat(executionScope.coroutineScope).isEqualTo(coroutineScope)
    }

    @Test
    fun `Should provide a shortcut to the execution config's coroutine context`() {
        // GIVEN
        val coroutineContext: CoroutineContext = mock()

        val executionConfig: ExecutionConfig = mock {
            on { context } doReturn coroutineContext
        }

        val executionScope = createExecutionScope(executionConfig)

        // THEN
        assertThat(executionScope.coroutineContext).isEqualTo(coroutineContext)
    }

    private fun createExecutionScope(config: ExecutionConfig) =
            object : ExecutionScope {
                override val executionConfig = config
            }
}