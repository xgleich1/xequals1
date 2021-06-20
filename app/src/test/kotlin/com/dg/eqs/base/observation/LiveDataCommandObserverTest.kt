package com.dg.eqs.base.observation

import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LiveDataCommandObserverTest {
    @Mock
    private lateinit var command: () -> Unit


    @Test
    fun `Should execute the command once notified`() {
        // GIVEN
        val observer = LiveDataCommandObserver(command)

        val liveDataCommand: LiveDataCommand = mock()

        // WHEN
        observer.onChanged(liveDataCommand)

        // THEN
        verify(liveDataCommand).execute(command)
    }
}