package com.dg.eqs.core.interaction.linking.choosing.widening

import android.content.res.Resources
import com.dg.eqs.R
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class WideningTimingTest {
    @Mock
    private lateinit var resources: Resources


    @Test
    fun `Should provide the delay after which an selection gets widened`() {
        // GIVEN
        setupResourcesToProvideWideningDelay(1500)

        val wideningTiming = WideningTiming(resources)

        // THEN
        assertThat(wideningTiming.delayInMs).isEqualTo(1500L)
    }

    private fun setupResourcesToProvideWideningDelay(delayInMs: Int) {
        whenever(resources.getInteger(R.integer.widening_delay_in_ms)) doReturn delayInMs
    }
}