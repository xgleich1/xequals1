package com.dg.eqs.base.observation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test


class LiveDataEventExtTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `A live data exposes its event once its emitted`() {
        // GIVEN
        val liveData = MutableLiveData<LiveDataEvent<Int>>()

        // WHEN
        liveData.emit(1)

        // THEN
        assertThat(liveData.value).isEqualTo(LiveDataEvent(1))
    }

    @Test
    fun `A live data exposes its event once its posted`() {
        // GIVEN
        val liveData = MutableLiveData<LiveDataEvent<Int>>()

        // WHEN
        liveData.post(1)

        // THEN
        assertThat(liveData.value).isEqualTo(LiveDataEvent(1))
    }
}