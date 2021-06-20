package com.dg.eqs.base.observation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test


class LiveDataCommandExtTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `A live data exposes its command once its emitted`() {
        // GIVEN
        val liveData = MutableLiveData<LiveDataCommand>()

        // WHEN
        liveData.emit()

        // THEN
        assertThat(liveData.value).isNotNull
    }

    @Test
    fun `A live data exposes its command once its posted`() {
        // GIVEN
        val liveData = MutableLiveData<LiveDataCommand>()

        // WHEN
        liveData.post()

        // THEN
        assertThat(liveData.value).isNotNull
    }
}