package com.dg.eqs.base.observation

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class LiveDataEventTest {
    @Test
    fun `Should compare two equal live data events`() {
        // GIVEN
        val eventA = LiveDataEvent(1)
        val eventB = LiveDataEvent(1)

        // THEN
        assertThat(eventA).isEqualTo(eventB)
    }

    @Test
    fun `Should convert a live data event to a string`() {
        // GIVEN
        val event = LiveDataEvent(1)

        // THEN
        assertThat(event).hasToString("LiveDataEvent(1)")
    }

    @Test
    fun `A live data event exposes its content only once`() {
        // GIVEN
        val event = LiveDataEvent(1)

        // THEN
        assertThat(event.getContentOnce()).isEqualTo(1)
        assertThat(event.getContentOnce()).isNull()
    }
}