package com.dg.eqs.base.observation

import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LiveDataEventObserverTest {
    @Mock
    private lateinit var recipient: (Int) -> Unit


    @Test
    fun `Should deliver the events content once notified`() {
        // GIVEN
        val observer = LiveDataEventObserver(recipient)

        val liveDataEvent = LiveDataEvent(1)

        // WHEN
        observer.onChanged(liveDataEvent)

        // THEN
        verify(recipient).invoke(1)
    }
}