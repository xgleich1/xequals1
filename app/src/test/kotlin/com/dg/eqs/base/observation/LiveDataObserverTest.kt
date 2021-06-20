package com.dg.eqs.base.observation

import org.mockito.kotlin.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class LiveDataObserverTest {
    @Mock
    private lateinit var recipient: (Int) -> Unit


    @Test
    fun `Should deliver the live datas value once notified`() {
        // GIVEN
        val observer = LiveDataObserver(recipient)

        // WHEN
        observer.onChanged(1)

        // THEN
        verify(recipient).invoke(1)
    }
}