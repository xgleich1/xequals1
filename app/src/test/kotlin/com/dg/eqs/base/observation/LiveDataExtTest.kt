package com.dg.eqs.base.observation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test


class LiveDataExtTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Test
    fun `A live data exposes its value once its emitted`() {
        // GIVEN
        val liveData = MutableLiveData<Int>()

        // WHEN
        liveData.emit(1)

        // THEN
        assertThat(liveData.value).isEqualTo(1)
    }

    @Test
    fun `A live data exposes its value once its posted`() {
        // GIVEN
        val liveData = MutableLiveData<Int>()

        // WHEN
        liveData.post(1)

        // THEN
        assertThat(liveData.value).isEqualTo(1)
    }
}