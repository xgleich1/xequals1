package com.dg.eqs.core.interaction.linking.choosing.widening

import com.dg.eqs.base.concurrency.BackgroundExecutorImpl
import org.mockito.kotlin.after
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class WideningDelayerTest {
    private companion object {
        private const val WIDENING_DELAY_IN_MS = 500L
        private const val VERIFY_TOLERANCE_IN_MS = 100L
    }

    @Mock
    private lateinit var wideningTiming: WideningTiming

    private lateinit var wideningDelayer: WideningDelayer

    @Mock
    private lateinit var widening: () -> Unit


    @Before
    fun setUp() {
        whenever(wideningTiming.delayInMs) doReturn WIDENING_DELAY_IN_MS

        wideningDelayer = WideningDelayer(BackgroundExecutorImpl(), wideningTiming)
    }

    @Test
    fun should_execute_the_widening_once_the_delay_has_passed() {
        // WHEN
        wideningDelayer.delay(widening)

        // THEN
        verifyWideningExecutedAfter(WIDENING_DELAY_IN_MS)
    }

    @Test
    fun should_not_execute_the_widening_before_the_delay_has_passed() {
        // WHEN
        wideningDelayer.delay(widening)

        // THEN
        verifyWideningNotExecutedAfter(WIDENING_DELAY_IN_MS / 2)
    }

    @Test
    fun should_cancel_the_current_widening_once_another_one_is_supplied() {
        // GIVEN
        wideningDelayer.delay(widening)

        // WHEN
        wideningDelayer.delay(widening)

        // THEN
        verifyWideningExecutedAfter(WIDENING_DELAY_IN_MS)
    }

    @Test
    fun should_cancel_the_current_widening_to_be_executed_after_the_delay() {
        // GIVEN
        wideningDelayer.delay(widening)

        // WHEN
        wideningDelayer.cancel()

        // THEN
        verifyWideningNotExecutedAfter(WIDENING_DELAY_IN_MS)
    }

    private fun verifyWideningExecutedAfter(delayInMs: Long) {
        val delayPlusTolerance = delayInMs + VERIFY_TOLERANCE_IN_MS

        verify(widening, after(delayPlusTolerance)).invoke()
    }

    private fun verifyWideningNotExecutedAfter(delayInMs: Long) {
        val delayPlusTolerance = delayInMs + VERIFY_TOLERANCE_IN_MS

        verify(widening, after(delayPlusTolerance).never()).invoke()
    }
}