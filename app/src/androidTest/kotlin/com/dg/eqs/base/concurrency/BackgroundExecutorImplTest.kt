package com.dg.eqs.base.concurrency

import com.dg.eqs.base.concurrency.ExecutionConfig.JobExecutionConfig
import com.dg.eqs.base.concurrency.ExecutionContext.MAIN
import com.dg.eqs.base.concurrency.ExecutionStart.IMMEDIATE
import com.dg.eqs.base.concurrency.ExecutionStart.LAZY
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext


@RunWith(MockitoJUnitRunner::class)
class BackgroundExecutorImplTest {
    private lateinit var countDownLatch: CountDownLatch

    private lateinit var backgroundExecutor: BackgroundExecutorImpl

    @Mock
    private lateinit var action: () -> Unit


    @Before
    fun setUp() {
        countDownLatch = CountDownLatch(1)

        backgroundExecutor = BackgroundExecutorImpl()
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_using_the_launch_immediately_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        // WHEN
        backgroundExecutor.launch(MAIN, IMMEDIATE) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_using_the_launch_lazily_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        val job = backgroundExecutor.launch(MAIN, LAZY) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        // WHEN
        job.start()

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_not_execute_an_action_without_starting_the_job_created_by_the_launch_lazily_mechanism() {
        // GIVEN
        backgroundExecutor.launch(MAIN, LAZY) {
            action.invoke()

            countdown()
        }

        // WHEN
        waitForCountdown()

        // THEN
        assert_action_not_executed()
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_using_the_async_immediately_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        // WHEN
        backgroundExecutor.async(MAIN, IMMEDIATE) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_using_the_async_lazily_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        val job = backgroundExecutor.async(MAIN, LAZY) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        // WHEN
        job.start()

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_not_execute_an_action_without_starting_the_job_created_by_the_async_lazily_mechanism() {
        // GIVEN
        backgroundExecutor.async(MAIN, LAZY) {
            action.invoke()

            countdown()
        }

        // WHEN
        waitForCountdown()

        // THEN
        assert_action_not_executed()
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_in_the_specified_scope_using_the_launch_immediately_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        // WHEN
        backgroundExecutor.launch(MAIN, TestExecutionScope(), IMMEDIATE) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_in_the_specified_scope_using_the_launch_lazily_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        val job = backgroundExecutor.launch(MAIN, TestExecutionScope(), LAZY) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        // WHEN
        job.start()

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_not_execute_an_action_in_the_specified_scope_without_starting_the_job_created_by_the_launch_lazily_mechanism() {
        // GIVEN
        backgroundExecutor.launch(MAIN, TestExecutionScope(), LAZY) {
            action.invoke()

            countdown()
        }

        // WHEN
        waitForCountdown()

        // THEN
        assert_action_not_executed()
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_in_the_specified_scope_using_the_async_immediately_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        // WHEN
        backgroundExecutor.async(MAIN, TestExecutionScope(), IMMEDIATE) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_execute_an_action_with_the_specified_context_in_the_specified_scope_using_the_async_lazily_mechanism() {
        // GIVEN
        var capturedContext: CoroutineContext? = null

        val job = backgroundExecutor.async(MAIN, TestExecutionScope(), LAZY) {
            capturedContext = coroutineContext

            action.invoke()

            countdown()
        }

        // WHEN
        job.start()

        waitForCountdown()

        // THEN
        assert_action_executed_with_context(capturedContext, Main)
    }

    @Test
    fun should_not_execute_an_action_in_the_specified_scope_without_starting_the_job_created_by_the_async_lazily_mechanism() {
        // GIVEN
        backgroundExecutor.async(MAIN, TestExecutionScope(), LAZY) {
            action.invoke()

            countdown()
        }

        // WHEN
        waitForCountdown()

        // THEN
        assert_action_not_executed()
    }

    @Test
    fun should_not_execute_an_action_using_the_launch_mechanism_when_the_specified_scope_is_cancelled() {
        // GIVEN
        val scope = TestExecutionScope()

        backgroundExecutor.launch(MAIN, scope, IMMEDIATE) {
            delay(1000L)

            action.invoke()

            countdown()
        }

        // WHEN
        backgroundExecutor.cancelExecutions(scope)

        waitForCountdown()

        // THEN
        assert_action_not_executed()
    }

    @Test
    fun should_not_execute_an_action_using_the_async_mechanism_when_the_specified_scope_is_cancelled() {
        // GIVEN
        val scope = TestExecutionScope()

        backgroundExecutor.async(MAIN, scope, IMMEDIATE) {
            delay(1000L)

            action.invoke()

            countdown()
        }

        // WHEN
        backgroundExecutor.cancelExecutions(scope)

        waitForCountdown()

        // THEN
        assert_action_not_executed()
    }

    private fun countdown() = countDownLatch.countDown()

    private fun waitForCountdown() = countDownLatch.await(5L, SECONDS)

    private fun assert_action_executed_with_context(actual: CoroutineContext?, expected: CoroutineContext) {
        verify(action).invoke()

        assertThat(actual.toString()).contains(expected.toString())
    }

    private fun assert_action_not_executed() = verify(action, never()).invoke()

    private class TestExecutionScope : ExecutionScope {
        override val executionConfig = JobExecutionConfig()
    }
}